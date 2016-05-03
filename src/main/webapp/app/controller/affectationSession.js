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
        self.SessionFormationList = [];
        Array.prototype.push.apply(self.SessionFormationList,data.data);

        function newSession(){
            var SessionConvertedList=[];
            for(var i=0 ; i<self.SessionFormationList.length ; i++){
                var SessionObjectConverted={
                    idSession: self.SessionFormationList[i].id,
                    idFormation: self.SessionFormationList[i].formation.id,
                    nom: self.SessionFormationList[i].formation.titreformation,
                    debut: $filter('date')(self.SessionFormationList[i].debut, 'dd/MM/yyyy'),
                    fin: $filter('date')(self.SessionFormationList[i].fin, 'dd/MM/yyyy'),
                    lieu: self.SessionFormationList[i].lieu
                };
                SessionConvertedList.push(SessionObjectConverted);
            }
            return SessionConvertedList;
        }
        self.SessionFormationListConverted= [];
        Array.prototype.push.apply(self.SessionFormationListConverted,newSession());
    });

    //Récupérer la liste des collaborateurs disponibles
    $http.get("api/collaborateurs").then(function(data){
        self.CollaborateurDisponibleList = [];
        self.SelectedCollaborateurList =[];
        Array.prototype.push.apply(self.CollaborateurDisponibleList,data.data);
        console.log("liste : ",self.CollaborateurDisponibleList);
       // self.SelectedCollaborateur=self.CollaborateurDisponibleList[0];
    });

    //déplace d'une liste à une autre
    self.moveItem = function(item,from,to){
        var idx=from.indexOf(item);
        if (idx != -1) {
            from.splice(idx, 1);
            to.push(item);
            self.SelectedCollaborateur.splice(0,self.SelectedCollaborateur.length);
        }
    };
    self.moveAll = function(from, to) {
        angular.forEach(from, function(item) {
            to.push(item);
        });
        from.length = 0;
    };
    self.CtrlSelectedItemTOEnableOrDisableButton = function(disponibleCollaborateur) {
        return typeof(disponibleCollaborateur) !== "undefined" && disponibleCollaborateur.length !== 0;
    };
    self.CtrlMoveAllTOEnableOrDisableButton = function(listDesCollaborateurs) {
        return listDesCollaborateurs.length === 0;
    };
    self.CtrlMaxCollaborateurSelectionnee = function(listDesCollaborateursSelectionnee) {
        return listDesCollaborateursSelectionnee.length === 10;
    };
}]);