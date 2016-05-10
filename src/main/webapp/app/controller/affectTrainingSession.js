//************************************************************************************//
//***** NAME: Controller Affectation session
//***** Description: moveItem / moveAll / CtrlItemIsSelectedTOEnableOrDisableButton
//*****              CtrlItemIsSelectedTOEnableOrDisableButton / CtrlMoveAllTOEnableOrDisableButton
//************************************************************************************//
angular.module('GestForController').
controller('CtrlAffectationSession',['$http','$location','$filter',function($http, $location,$filter){

    var self = this;
    //Récupérer la liste des sessions disponible
    $http.get("api/sessions").then(function(data){
        self.trainingSessionList = [];
        Array.prototype.push.apply(self.trainingSessionList,data.data);

        function newSession(){
            var sessionConvertedList=[];
            for(var i=0 ; i<self.trainingSessionList.length ; i++){
                var sessionObjectConverted={
                    sessionId: self.trainingSessionList[i].id,
                    trainingId: self.trainingSessionList[i].training.id,
                    trainingTitle: self.trainingSessionList[i].training.trainingTitle,
                    beginning: $filter('date')(self.trainingSessionList[i].beginning, 'dd/MM/yyyy'),
                    ending: $filter('date')(self.trainingSessionList[i].ending, 'dd/MM/yyyy'),
                    location: self.trainingSessionList[i].location
                };
                sessionConvertedList.push(sessionObjectConverted);
            }
            return sessionConvertedList;
        }
        self.trainingSessionListConverted  = [];
        Array.prototype.push.apply(self.trainingSessionListConverted,newSession());
    });

    //Récupérer la liste des collaborateurs disponibles
    $http.get("api/collaborateurs").then(function(data){
        self.availableCollaboratorList = [];
        self.selectedCollaboratorList =[];
        Array.prototype.push.apply(self.availableCollaboratorList,data.data);
        console.log("liste : ",self.availableCollaboratorList);
       // self.selectedCollaborator=self.availableCollaboratorList[0];
    });

    //déplace d'une liste à une autre
    self.moveItem = function(item,from,to){
    	console.log("item",item);
        var idx=from.indexOf(item);
        if (idx != -1) {
            from.splice(idx, 1);
            to.push(item);
        }
    };
    self.moveAll = function(from, to) {
        angular.forEach(from, function(item) {
            to.push(item);
        });
        from.length = 0;
    };
    self.CtrlSelectedItemTOEnableOrDisableButton = function(availableCollaborator) {
        return typeof(availableCollaborator) !== "undefined" && availableCollaborator.length !== 0;
    };
    self.CtrlMoveAllTOEnableOrDisableButton = function(collaboratorList) {
        return collaboratorList.length === 0;
    };
    self.CtrlMaxCollaboratorSelectionnee = function(selectedCollaboratorList) {
        return selectedCollaboratorList.length === 10;
    };
}]);