angular.module('controllers')
    .controller('controllerRegisterCollaborator', ['$http', '$location', 'hash', function ($http, $location, hash) {
        var self = this;
        self.regex = {};
        self.isNewPersonalIdNumber = true;
        self.isNewEmail = true;
        self.isFalseForm = false;
        self.isThereAnEmptyField = false;



        /*** Recupération des regex ***/
        $http.get("api/collaborateurs/regex").then(function (data) {
            self.regex.personnalIdNumber = new RegExp(data.data.PERSONNAL_ID_NUMBER);
            self.regex.lastName = new RegExp(data.data.LAST_NAME);
            self.regex.firstName = new RegExp(data.data.FIRST_NAME);
            self.regex.email = new RegExp(data.data.EMAIL);
        });

        self.isErrorInputMessageDisplayed = function (inputForm, focus) {
            return !inputForm.$error.required && inputForm.$invalid && !focus;
        };

        self.verifyForm = function (collaboratorForm) {
            self.isNewPersonalIdNumber = true;
            self.isNewEmail = true;
            self.isFalseForm = false;
            self.isThereAnEmptyField = false;
            if (collaboratorForm.$error.required) {
                checkEmptyFields(collaboratorForm);
                self.isThereAnEmptyField = true;
                self.isFalseForm = false;
            }
            else if (collaboratorForm.$invalid) {
                self.isFalseForm = true;
                self.isThereAnEmptyField = false;
            }
            else {
                var collaboratorToRegister = JSON.parse(JSON.stringify(self.collaborator));
                self.saveAction(collaboratorToRegister);
            }
        };

        self.saveAction = function (collaboratorToRegister) {
            //delete useless spaces between words 
            collaboratorToRegister.lastName = collaboratorToRegister.lastName.replace(/ +/g, " ");
            collaboratorToRegister.firstName = collaboratorToRegister.firstName.replace(/ +/g, " ");
            //Crypter le mot de passe (Algorithme sha1)
            collaboratorToRegister.password = hash(collaboratorToRegister.password);
            delete collaboratorToRegister['confirmPassword'];
            //post the form to the server
            $http.post("api/collaborateurs", collaboratorToRegister).success(function (data) {
                self.isThereAnEmptyField = false;
                if (data.response === "NotPersisted") {
                    resetForm(); //Reset the Form
                    self.isNewEmail = true;
                    self.isNewPersonalIdNumber = true;
                    $location.url('/Authentication');
                }
                else if (data.response === "IdNumberPersisted") {
                    self.isNewPersonalIdNumber = false;
                    self.isNewEmail = true;
                }
                else {
                    self.isNewEmail = false;
                    self.isNewPersonalIdNumber = true;
                }
                data.response = undefined;
            });
        };

        //Variables pour les champs
        var intiateAllVariable = function () {
            self.isEmptyMatriculeField = false;
            self.isEmptyFirstNameField = false;
            self.isEmptyLastNameField = false;
            self.isEmptyEmailField = false;
            self.isEmptyPwdField = false;
            self.isEmptyConfirmPwdField = false;
        };

        var checkEmptyFields = function (form) {
            intiateAllVariable();
            if (form.personnalIdNumber.$invalid) {
                self.isEmptyMatriculeField = true;
            }
            if (form.firstName.$invalid) {
                self.isEmptyFirstNameField = true;
            }
            if (form.lastName.$invalid) {
                self.isEmptyLastNameField = true;
            }
            if (form.email.$invalid) {
                self.isEmptyEmailField = true;
            }
            if (form.password.$invalid) {
                self.isEmptyPwdField = true;
            }
            if (form.confirmPassword.$invalid) {
                self.isEmptyConfirmPwdField = true;
            }
        };

        //Reset the Form
        resetForm = function () {
            self.collaborator.personnalIdNumber = "";
            self.collaborator.lastName = "";
            self.collaborator.firstName = "";
            self.collaborator.email = "";
            self.collaborator.password = "";
            self.collaborator.confirmPassword = "";
        }
    }])
    .directive('pwCheck', [function () {
        return {
            require: "ngModel",
            scope: {
                otherModelValue: "=pwCheck"
            },
            link: function (scope, element, attributes, ngModel) {
                ngModel.$validators.pwCheck = function (modelValue) {
                    return modelValue === scope.otherModelValue;
                };
                scope.$watch("otherModelValue", function () {
                    ngModel.$validate();
                });
            }
        }
    }])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/RegisterCollaborator', {
                templateUrl: 'templates/registerCollaborator.html',
                controller: 'controllerRegisterCollaborator',
                controllerAs: 'EC'
            })
    }
    ])
;
