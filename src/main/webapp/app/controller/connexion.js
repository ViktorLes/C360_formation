angular.module('controllers')
    .controller('controllerConnexion', ['$http', '$location', function ($http, $location) {
        var self = this;


        self.NouveauUser = function (collaborator) {
            $location.url("/registerCollaborator");
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