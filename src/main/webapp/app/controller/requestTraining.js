angular.module('controllers')
    .controller('controllerRequestTraining',['$http', '$location',function($http, $location) {
        var self = this;
        //Charge la liste de formations affiché dans le select box des formations
        $http.get("api/formations").then(function(data){
            self.trainings = [];
            Array.prototype.push.apply(self.trainings,data.data);
        });

        //Charge la liste de sessions disponible en fonction de l'ID de training
        //selectionné grâce au 'select box' 
        self.loadTrainingSessions=function() {
            self.noneSessionSelected = false;
            self.hasToChooseOneTraining = false;
            self.listTrainingSession = [];
            if (Number.isInteger(self.requestedTraining.id)){
                $http.get("api/formations/"+self.requestedTraining.id+"/sessions").then(function (data) {
                    Array.prototype.push.apply(self.listTrainingSession, data.data);
                    if(self.listTrainingSession.length === 0) {
                        self.isListEmpty = true;
                    }
                    else self.isListEmpty = false;
                });
            }
        };

        self.verifyForm = function () {
            self.noneSessionSelected = false;
            self.hasToChooseOneTraining = false;
            if (self.requestedTraining) {
                if (typeof self.listTrainingSession !== 'undefined' ) {
                    if(self.isListEmpty){
                        //envoi au serveur une demande de session non programmée
                    	self.saveAction();
                    }else if(self.listTrainingSession.some(function (elem) {return elem.isChecked;})){
                        //envoi 'des' sessions selectionné par le collaborateur au serveur
                    	self.saveAction();
                    }else{
                        self.noneSessionSelected = true;
                    }
                }
            }else {
                self.hasToChooseOneTraining = true;
            }
        };

        self.saveAction = function() {
            var getSessionsSelected = function(listTrainingSession) {
                var listToSend=[];
                listTrainingSession.forEach(function (elem) {
                   if(elem.isChecked) listToSend.push(elem);
                });
                listToSend.forEach(function(elem){
                    delete elem.isChecked;
                });
                return listToSend;
            };
        	var myRequest={
        			training: self.requestedTraining,
                    collaborator: 2,
        			trainingSessions: getSessionsSelected(self.listTrainingSession)
        	};
        	$http.post("api/requests", myRequest).success(function(data){
                if(data === true || data === "true") {
                    document.location.href = 'pageblanche.html';
                }
        	});
        };
    }]);