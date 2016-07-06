angular.module('controllers')
    .controller('ctrlManageSession', ['$http', '$location', 'SelectTrainingService', function ($http, $location, SelectTrainingService) {
        var self = this;
        self.listTrainingSession=[];

        self.training = SelectTrainingService.get();
        /*** Recupération les sessions **/
        $http.get("api/formations/" + self.training.id + "/sessions").then(function (data) {
            self.listTrainingSession=data.data;
        });

        self.registerTrainingSession = function () {
            $location.url("/RegisterTrainingSession");
        };
        self.returnToRegisterTraining = function () {
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