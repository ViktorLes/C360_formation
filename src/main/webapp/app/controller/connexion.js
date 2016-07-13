angular.module('controllers')
    .controller('controllerConnexion', ['$http', '$location', function ($http, $location) {
        var self = this;
        self.isThereAnEmptyField = false;

        self.verifyForm = function (userForm) {
            if (userForm.$error.required) {
                self.isThereAnEmptyField = true;
            }
            else if (userForm.$invalid) {
                self.isThereAnEmptyField = false;
            }
        };
        self.NouveauUser = function () {
            $location.url("/RegisterCollaborator");
        };
        
        self.SeConnecter = function () {
            $http.post("api/users",user).success(function (data) {
          
        };
    }])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/connexion', {
                templateUrl: 'templates/connexion.html',
                controller: 'controllerConnexion',
                controllerAs: 'CC'
            });
    }
    ]);