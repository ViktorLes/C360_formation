angular.module('controllers')
    .controller('controllerManageSession', ['$http', '$location', function ($http, $location) {
        var self = this;
        
        /*** Recupération des formations **/
        $http.get("api/formations").then(function (data) {
            self.trainings = data.data;
        });
        
        self.registerTrainingSession = function (training) {
            $location.url("/registerTrainingSession");
        };
    }])
    
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/ManageSession', {
                templateUrl: 'templates/manageSession.html',
                controller: 'controllerManageSession',
                controllerAs: 'MS'
            })
    }
    ]);