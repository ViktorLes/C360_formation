angular.module('controllers')
    .controller('controllerRegisterTrainingSession', ['DatepickerService', '$http', '$filter', '$location', function (datepicker, $http, $filter, $location) {
        var self = this;
        self.isSessionAlreadyPlanned = false;

        $http.get("api/formations").then(function (data) {
            self.trainings = [];
            Array.prototype.push.apply(self.trainings, data.data);
            self.training = self.trainings[0];
        });

        self.d1 = datepicker.build();
        self.d2 = datepicker.build();

        function initTimeSlot() {
            function pad2(number) {
                return (number < 10 ? '0' : '') + number;
            }

            var myArray = [];
            var beginningHour = 8;
            var endHour = 18;
            var feet = 30;
            var endMinute = 30;
            var beginningMinute = 0;
            var numberFeetHour = 60 / feet;
            var numberHours = (endHour - beginningHour) * numberFeetHour;
            var numberFeetMinutes = (endMinute - beginningMinute) / feet;

            for (var recorder = 0; recorder < (numberHours + numberFeetMinutes); recorder++) {
                myArray.push(pad2((beginningHour + Math.floor(recorder / numberFeetHour))).toString() + ":" + pad2((recorder % numberFeetHour * feet)).toString());
            }
            self.timeSlotsTraining = myArray;
        }

        initTimeSlot();
        self.beginningHour = self.timeSlotsTraining[0];
        self.endHour = self.timeSlotsTraining[0];

        self.trainingLocation = 'Salle Phuket';
        self.isFalseForm = false;

        self.verifyForm = function (sessionFormIsInvalid) {
            if (sessionFormIsInvalid == false) {
                self.saveAction();
            }
            else {
                self.isFalseForm = true;
            }
        };


        self.DateCorrect = function (beginningHour, endHour) {
            if ((self.d1.dt < self.d2.dt) || (self.beginningHour < self.endHour)) {
                return true;
            }
            else {
                return false;
            }
        }

        self.isWeekENDD2 = function () {
            return (self.d2.dt.getDay() == 0 || self.d2.dt.getDay() == 6);
        }

        self.isWeekENDD1 = function () {
            return (self.d1.dt.getDay() == 0 || self.d1.dt.getDay() == 6);
        }

        self.showForm = function (form) {
            console.log("showForm >>>>>>>>>>>>>>>>>>>>>", form)
        }

        /*** Enregistrement SessionFormation ***/

        self.saveAction = function () {
            var session = {
                training: self.training,
                beginning: $filter('date')(self.d1.dt, "dd/MM/yyyy"),
                ending: $filter('date')(self.d2.dt, "dd/MM/yyyy"),
                beginningTime: self.beginningHour,
                endingTime: self.endHour,
                location: self.trainingLocation
            };
            $http.post("api/sessions", session).success(function (data) {
                if (data == "true" || data == true) {
                    self.isSessionAlreadyPlanned = false;
                    $location.url('/pageblanche');
                } else {
                    self.isSessionAlreadyPlanned = true;
                }
            });
        }
    }])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/RegisterTrainingSession', {
                templateUrl: 'templates/registerTrainingSession.html',
                controller: 'controllerRegisterTrainingSession',
                controllerAs: 'DS'
            });
    }
    ]);