//************************************************************************************//
//***** NAME: Controller Affectation session
//***** Description: moveItem / moveAll / CtrlItemIsSelectedTOEnableOrDisableButton
//*****              CtrlItemIsSelectedTOEnableOrDisableButton / CtrlMoveAllTOEnableOrDisableButton
//************************************************************************************//
angular.module('GestForController').
controller('CtrlAffectationSession',['$scope','$http','$location','$filter',function($scope,$http, $location,$filter){

    var self = this;
    //Récupérer la liste des sessions disponible
    $http.get("api/sessions").then(function(data){
        self.SessionFormationList = [];
        Array.prototype.push.apply(self.SessionFormationList,data.data);

        function NouvelleSession(){
            var SessionConvertedList=[];
            for(var i=0 ; i<self.SessionFormationList.length ; i++){
                var SessionObjectConverted={
                    idSession: self.SessionFormationList[i].id,
                    idFormation: self.SessionFormationList[i].formation.id,
                    nom: self.SessionFormationList[i].formation.titreformation,
                    debut: $filter('date')(self.SessionFormationList[i].debut, 'dd/MM/yyyy'),
                    fin: $filter('date')(self.SessionFormationList[i].fin, 'dd/MM/yyyy'),
                    lieu: self.SessionFormationList[i].lieu
                }
                SessionConvertedList.push(SessionObjectConverted);
            }
            return SessionConvertedList;
        };
        self.SessionFormationListConverted= [];
        Array.prototype.push.apply(self.SessionFormationListConverted,NouvelleSession());
    });

    //Récupérer la liste des collaborateur disponible
    $http.get("api/collaborateurs").then(function(data){
        self.CollaborateurDisponibleList = [];
        self.SelectedCollaborateurList =[];
        Array.prototype.push.apply(self.CollaborateurDisponibleList,data.data);
        self.SelectedCollaborateur=self.CollaborateurDisponibleList[0];
    });

    //moveItem d'une liste à une autre
    $scope.moveItem = function(item,from,to){
        var idx=from.indexOf(item);
        if (idx != -1) {
            from.splice(idx, 1);
            to.push(item);
        }
    };
    $scope.moveAll = function(from, to) {
        angular.forEach(from, function(item) {
            to.push(item);
        });
        from.length = 0;
    };
    self.CtrlSelectedItemTOEnableOrDisableButton = function(disponibleCollaborateur) {
        if(typeof(disponibleCollaborateur) == "undefined" || disponibleCollaborateur.length ==0){
            return false;
        }
        else
            return true;
    };
    self.CtrlMoveAllTOEnableOrDisableButton = function(listDesCollaborateurs) {
        if(listDesCollaborateurs.length==0){
            return true;
        }
        else
            return false;
    };
    self.CtrlMaxCollaborateurSelectionnee = function(listDesCollaborateursSelectionnee) {
        if(listDesCollaborateursSelectionnee.length==10){
            return true;
        }
        else
            return false;
    };
}]);