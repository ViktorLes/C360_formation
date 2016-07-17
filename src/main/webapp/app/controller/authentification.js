angular.module('controllers')
    .controller('controllerAuthentification', ['$http', '$location', 'hash','currentUserService', function ($http, $location, hash,currentUserService) {
        var self = this;
        self.isNotEmptyEmailField = true;
        self.isNotEmptyPasswordField = true;
        self.isErrorAuthentification = false;
        var connectedUser ="";

        self.submitLogin = function (userForm) {
            if (validateForm(userForm)) {
                self.user.password = hash(self.user.password);
                $http.post("api/user", self.user).success(function (userPersistedToken) {
                    self.isErrorAuthentification = false;
                    connectedUser = currentUserService.getUserNameFromServer(Object.keys(userPersistedToken)[0]);
                    currentUserService.setUserToken(userPersistedToken,connectedUser);
                    self.user.email = "";
                    self.user.password = "";
                }).error(function () {
                    self.user.password = "";
                    self.isErrorAuthentification = true;
                });
            }
        };
        var validateForm = function (userForm) {
            if (userForm.email.$invalid) self.isNotEmptyEmailField = false;
            if (userForm.password.$invalid) self.isNotEmptyPasswordField = false;
            return self.isNotEmptyEmailField && self.isNotEmptyPasswordField;
        };
        self.registerNewCollaborator = function () {
            $location.url("/RegisterCollaborator");
        };
    }])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/authentification', {
                templateUrl: 'templates/authentication.html',
                controller: 'controllerAuthentification',
                controllerAs: 'CX'
            });
    }
    ]);