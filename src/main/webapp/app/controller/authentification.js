angular.module('controllers')
    .controller('controllerAuthentification', ['$http', '$location', 'hash', 'currentUserService', '$timeout', function ($http, $location, hash, currentUserService, $timeout) {
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
                        self.user.email = "";
                        self.user.password = "";
                        self.isErrorAuthentification = false;
                        self.isUserAuthenticated = true;
                        currentUserService.decodeThisToken(userPersistedToken);
                    }).then(function () {
                    return $timeout(function () {
                        self.isUserAuthenticated = false;
                    }, 3000);
                }).then(function () {
                    if (currentUserService.getUserRole()) {
                        $location.url('/RegisterTraining')
                    }
                    else {
                        $location.url('/RequestTraining')
                    }
                })
                    .catch(function () {
                        self.user.password = "";
                        self.isErrorAuthentification = true;
                        self.isUserAuthenticated = false;
                    });
            }
        };
        var validateForm = function (userForm) {
            if (userForm.email.$invalid) {
                self.isNotEmptyEmailField = false;
                self.isErrorAuthentification = true;
            }
            if (userForm.password.$invalid) {
                self.isNotEmptyPasswordField = false;
                self.isErrorAuthentification = true;
            }
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