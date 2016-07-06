angular.module('controllers')
    .controller('ctrlManageSession', ['$http', '$location', 'SelectTrainingService', function ($http, $location, SelectTrainingService) {
        var self = this;
        console.log("hfqsjhf");
        var tempTraining = {
            id: 3,
            numberHalfDays: 1,
            trainingTitle: "AngularJS",
            topicDescription: {
                id: 1,
                name: "Développement Web"
            }
        };
        self.training = SelectTrainingService.get();
        /*** Recupération des formations **/
        $http.get("api/formations/" + self.training.id + "/sessions").then(function (data) {
            console.log(data);
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