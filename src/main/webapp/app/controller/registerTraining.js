angular.module('controllers')
    .controller('controllerRegisterTraining', ['$http', '$location', function ($http, $location) {

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
            $http.post("api/formations", self.training).success(function (data) {
                if (data == "true" || data == true) {
                    self.isNewTrainingTitle = true;
                    $location.url('/pageblanche');
                }
                else {
                    self.isNewTrainingTitle = false;
                }
            });
        }
    }])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/RegisterTraining', {
                templateUrl: 'templates/registerTraining.html',
                controller: 'controllerRegisterTraining',
                controllerAs: 'DF'
            })
    }
    ]);