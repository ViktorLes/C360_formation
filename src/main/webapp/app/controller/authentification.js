angular.module('controllers')
    .controller('controllerAuthentification', ['$http', '$location', 'hash','currentUserService','$timeout', function ($http, $location, hash,currentUserService,$timeout) {
        var self = this;
        self.isNotEmptyEmailField = true;
        self.isNotEmptyPasswordField = true;
        self.isErrorAuthentification = false;
        self.isUserAuthenticated = false;

        self.submitLogin = function (userForm) {
            if (validateForm(userForm)) {
                self.user.password = hash(self.user.password);
                $http.post("api/user", self.user)
                    .then(function (userPersistedToken) {
                        self.userToken = Object.keys(userPersistedToken.data)[0];
                        return currentUserService.getUserNameFromServer(Object.keys(userPersistedToken.data)[0]) })
                    .then(function (currentUserName) {
                        self.isErrorAuthentification = false;
                        self.user.email = "";
                        self.user.password = "";
                        return currentUserService.setUserData(currentUserName, self.userToken);
                    }).then(function () {
                })
                    .catch(function () {
                    self.user.password = "";
                    self.isErrorAuthentification = true;
                });
            }
            if(!self.isErrorAuthentification){
                self.isUserAuthenticated = true;
                self.setConfirmationMessageTimOut();
            }
        };
        self.setConfirmationMessageTimOut = function () {
            $timeout(function () {
                self.isUserAuthenticated = false;
                $location.url('/RegisterTraining');
            }, 3000);
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