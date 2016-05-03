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
                    idSession: self.trainingSessionList[i].id,
                    idFormation: self.trainingSessionList[i].training.id,
                    nom: self.trainingSessionList[i].training.trainingTitle,
                    debut: $filter('date')(self.trainingSessionList[i].debut, 'dd/MM/yyyy'),
                    fin: $filter('date')(self.trainingSessionList[i].fin, 'dd/MM/yyyy'),
                    lieu: self.trainingSessionList[i].lieu
                };
                sessionConvertedList.push(sessionObjectConverted);
            }
            return sessionConvertedList;
        }
        self.trainingSessionListConverted= [];
        Array.prototype.push.apply(self.trainingSessionListConverted,newSession());
    });

    //Récupérer la liste des collaborateurs disponibles
    $http.get("api/collaborateurs").then(function(data){
        self.AvailableCollaboratorList = [];
        self.SelectedCollaborateurList =[];
        Array.prototype.push.apply(self.AvailableCollaboratorList,data.data);
        console.log("liste : ",self.AvailableCollaboratorList);
       // self.SelectedCollaborateur=self.AvailableCollaboratorList[0];
    });

    //déplace d'une liste à une autre
    self.moveItem = function(item,from,to){
        var idx=from.indexOf(item);
        if (idx != -1) {
            from.splice(idx, 1);
            to.push(item);
            self.SelectedCollaborator.splice(0,self.SelectedCollaborator.length);
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
    self.CtrlMaxCollaborateurSelectionnee = function(selectedCollaboratorList) {
        return selectedCollaboratorList.length === 10;
    };
}]);