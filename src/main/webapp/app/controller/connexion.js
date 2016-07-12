angular.module('controllers')
    .controller('ctrlconnexion', ['$http', '$location', function ($http, $location) {
        var self = this;
    }])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/Connexion', {
                templateUrl: 'templates/connexion.html',
                controller: 'ctrlConnexion',
                controllerAs: 'CC'
            })
    }
    ]);