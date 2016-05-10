angular.module('controllers')
    .controller('controllerRegisterTrainingSession', ['DatepickerService','$http','$filter',function(datepicker,$http,$filter) {
        var self = this;
        self.isSessionAlreadyPlanned = true;

        $http.get("api/formations").then(function(data){
            self.training =[];
            Array.prototype.push.apply(self.training,data.data);
            self.trainingSessionId = self.training[0].id;
        });

        self.d1 = datepicker.build();
        self.d2 = datepicker.build();

        function initTimeSlot(){
            function pad2(number) {
                return (number < 10 ? '0' : '') + number;
            }
            var myArray =[];
            var beginningHour=8; var endHour=18; var feet=30; var endMinute=30; var beginningMinute=0;
            var numberFeetHour = 60/feet;
            var numberHours = (endHour-beginningHour)*numberFeetHour;
            var numberFeetMinutes = (endMinute-beginningMinute)/feet;

            for(var recorder=0; recorder<(numberHours+numberFeetMinutes); recorder++)
            {
                myArray.push(pad2((beginningHour + Math.floor(recorder/numberFeetHour))).toString() + ":" + pad2((recorder%numberFeetHour*feet)).toString());
            }
            self.timeSlotsTraining = myArray;
        }
        initTimeSlot();
        self.beginningHour = self.timeSlotsTraining[0];
        self.endHour = self.timeSlotsTraining[0];

        self.trainingLocation = 'Salle Phuket';
        self.isFalseForm = false;

        self.verifierForm=function(sessionForm){
            if(sessionForm.$invalid == false){
                self.actionEnregistrer();
            }
            else{
                self.isFalseForm = true;
            }
        };


        self.DateCorrect = function(beginningHour,endHour) {
            if ((self.d1.dt < self.d2.dt)||(self.beginningHour < self.endHour)){
                return true;
            }
            else
            {
                return false;
            }
        }

        self.isWeekENDD2 = function(){
            return (self.d2.dt.getDay()== 0 || self.d2.dt.getDay()== 6);
        }

        self.isWeekENDD1 = function(){
            return (self.d1.dt.getDay()== 0 || self.d1.dt.getDay()== 6);
        }

        self.showForm = function(form){
            console.log("showForm >>>>>>>>>>>>>>>>>>>>>",form)
        }

        /*** Enregistrement SessionFormation ***/

        self.actionEnregistrer = function() {
            var session = {
                training: self.trainingSessionId,
                beginning: $filter('date')(self.d1.dt,"dd/MM/yyyy") + "|" + self.beginningHour,
                ending:  $filter('date')(self.d2.dt,"dd/MM/yyyy") + "|" + self.endHour,
                location: self.trainingLocation
            };

            $http.post("api/sessions", session).success(function(data){
                if(data == "true" || data == true) {
                    self.isSessionAlreadyPlanned = true;
                    document.location.href = 'pageblanche.html';
                }else
                {
                    self.isSessionAlreadyPlanned = false;
                }
            });
        }

    }]);