angular.module('controllers')
    .controller('ctrlManageSession', ['$http', '$location', function ($http, $location) {
        var self = this;
        
        /*** Recupération des formations **/
        $http.get("api/formations").then(function (data) {
            self.trainings = data.data;
        });
        
        self.registerTrainingSession = function () {
            $location.url("/RegisterTrainingSession");
        };
        self.returnToRegisterTraining=function () {
          $location.url("/RegisterTraining");
        };
    }])
    
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/ManageSession', {
                templateUrl: 'templates/manageSession.html',
                controller: 'ctrlManageSession',
                controllerAs: 'MS'
            })
    }
    ]);