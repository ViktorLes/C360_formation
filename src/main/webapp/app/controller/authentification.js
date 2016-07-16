angular.module('controllers')
    .controller('controllerAuthentification', ['$http', '$location', 'hash', 'connectedUserService', function ($http, $location, hash, connectedUserService) {
        var self = this;
        self.isNotEmptyEmailField = true;
        self.isNotEmptyPasswordField = true;

        self.submitLogin = function (userForm) {
            if (validateForm(userForm)) {
                self.user.password = hash(self.user.password);
                $http.post("api/user", self.user).success(function (userPersisted) {
                    self.user.email = "";
                    self.user.password = "";
                    connectedUserService.setUser(userPersisted);
                }).error(function () {
                    self.user.password = "";
                    console.log("erreur");
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