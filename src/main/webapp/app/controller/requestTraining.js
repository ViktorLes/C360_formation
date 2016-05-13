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
            console.log(self.requestedTraining);
            self.noneSessionSelected = false;
            self.hasToChooseOneTraining = false;
            self.listTrainingSession = [];
            if (Number.isInteger(self.requestedTrainingId)) $http.get("api/formations/"+self.requestedTraining.id+"/sessions").then(function (data) {
                Array.prototype.push.apply(self.listTrainingSession, data.data);
                if(self.listTrainingSession.length === 0) {
                    self.isListEmpty = true;
                }
                else self.isListEmpty = false;
            });
        }

        self.verifyForm = function () {
            self.noneSessionSelected = false;
            self.hasToChooseOneTraining = false;
            if (Number.isInteger(self.requestedTraining.id)) {
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
        }

        self.saveAction = function() {
        	/*var listIdTrainingSessions=[];
        	self.listTrainingSession.forEach(function(element){
        		if(element.isChecked===true){
        			listIdTrainingSessions.push(element.id);
        		}
        	});*/
        	var myRequest={
        			training:self.requestedTraining,
        			trainingSessions:self.listTrainingSession,
        			collaborator:4
        	};
            console.log(myRequest);
        	$http.post("api/requests", myRequest).success(function(data){
                console.log(data);
                if(data === true || data === "true") {
                    document.location.href = 'pageblanche.html';
                }
        	});
        }

    }]);