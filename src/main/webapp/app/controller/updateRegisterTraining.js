angular.module('controllers')
    .controller('controllerUpdateRegisterTraining', ['$http', '$location', '$filter', function ($http, $location) {

        var self = this;
        self.regex = {};
        /*** Recupération des regex **/
        $http.get("api/formations/regex").then(function (data) {
            self.regex.trainingTitle = new RegExp(data.data.TRAINING_TITLE);
            self.regex.numberHalfDays = new RegExp(data.data.NUMBER_HALF_DAYS);
        });

        /*** Recupération des Thèmes **/
        $http.get("api/themes").then(function (data) {
            self.topicList = data.data;
        });

        /*** Recupération des formations **/
        $http.get("api/formations").then(function (data) {
            self.trainingList = data.data;
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
            $http.post("api/formations", self.training).success(function (data) {
                if (data == "true" || data == true) {
                    self.isNewTrainingTitle = true;
                    self.trainingList.push(self.training);
                }
                else {
                    self.isNewTrainingTitle = false;
                }
            });
        };

        var indexedTeams = [];
        self.returnTrainingListToFilter = function () {
            indexedTeams = [];
            return self.trainingList;
        };
        self.filterTopic = function (training) {
            var isNewTopic = indexedTeams.indexOf(training.topicDescription.name) == -1;
            if (isNewTopic) indexedTeams.push(training.topicDescription.name);
            return isNewTopic;
        };
        self.manageSession = function (training) {
            $location.url("/manageSession");
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