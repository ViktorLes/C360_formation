angular.module('controllers')
    .controller('controllerUpdateRegisterTraining', ['$http', '$location', '$filter', function ($http, $location) {

        var self = this;
        self.regex = {};
        /*** Recupération des regex **/
        $http.get("api/formations/regex").then(function (data) {
            self.regex.trainingTitle = new RegExp(data.data.TRAINING_TITLE);
            self.regex.numberHalfDays = new RegExp(data.data.NUMBER_HALF_DAYS);
        });
        self.isNewTrainingTitle = true;
        self.isFalseForm = false;
        self.isThereAnEmptyField = false;

        self.isErrorInputMessageDisplayed = function (inputForm, focus) {
            return !inputForm.$error.required && inputForm.$invalid && !focus;
        };

        self.verifyForm = function (trainingForm) {
            if (trainingForm.$error.required) {
                self.isThereAnEmptyField = true;
                self.isFalseForm = false;
            }
            else if (trainingForm.$invalid) {
                self.isFalseForm = true;
                self.isThereAnEmptyField = false;
            }
            else {
                self.saveAction();
            }
        };

        self.saveAction = function () {
            self.training.trainingTitle = self.training.trainingTitle.replace(/ +/g, " ");
            console.log("object: ", self.training);
            $http.post("api/formations", self.training).success(function (data) {
                if (data == "true" || data == true) {
                    self.isNewTrainingTitle = true;
                    $location.url('/pageblanche');
                }
                else {
                    self.isNewTrainingTitle = false;
                }
            });
        };

//Afficher les sessions par thèmes
        var topic1 = {name: "Développement Mobile"};
        var topic2 = {name: "Développement Web"};
        self.topicList = [topic1, topic2];
        // A supprimer après l'intégration du Back
        self.trainingList = [
            {
                "id": 1,
                "trainingTitle": "KKK",
                "topic": topic1,
                "numberHalfDays": 5
            },
            {
                "id": 2,
                "trainingTitle": "HHHHHHHHHHHHH",
                "topic": topic2,
                "numberHalfDays": 4
            },
            {
                "id": 3,
                "trainingTitle": "LLL",
                "topic": topic1,
                "numberHalfDays": 2
            }];
        ////////////////////////////////////////
        var indexedTeams = [];
        self.returnTrainingListToFilter = function() {
            indexedTeams = [];
            return self.trainingList;
        };
        self.filterTopic = function (training) {
            var isNewTopic = indexedTeams.indexOf(training.topic.name) == -1;
            if (isNewTopic) indexedTeams.push(training.topic.name);
            return isNewTopic;
        };
        self.manageSession=function (training) {
            $location.url("/RegisterTrainingSession");
        }
    }])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/UpdateRegisterTraining', {
                templateUrl: 'templates/updateRegisterTraining.html',
                controller: 'controllerUpdateRegisterTraining',
                controllerAs: 'DF'
            })
    }
    ]);