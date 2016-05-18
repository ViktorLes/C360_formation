//************************************************************************************//
//***** NAME: Controller Affectation session
//***** Description: moveItem / moveAll / CtrlItemIsSelectedTOEnableOrDisableButton
//*****              CtrlItemIsSelectedTOEnableOrDisableButton / CtrlMoveAllTOEnableOrDisableButton
//************************************************************************************//
angular.module('controllers').
controller('controllerAffectTraining',['$http','$location','$filter',function($http, $location,$filter){

    var self = this;
    self.booleanVariable=false;
    //Récupérer la liste des sessions disponible
    $http.get("api/sessions").then(function(data){
        self.trainingSessionList = data.data;
        self.sessionSelected = self.trainingSessionList[0];
    });

    //Récupérer la liste des collaborateurs disponibles
    $http.get("api/collaborateurs").then(function(data){
        self.availableCollaboratorList = data.data;
        self.selectedCollaboratorList=[];
        console.log("liste : ",self.availableCollaboratorList);
    });

    self.displayTrainingSession = function(mySession){
        return mySession.training.trainingTitle+' - '+mySession.beginning+' à '+mySession.ending+' - '+mySession.location;
    };

    //déplace d'une liste à une autre
    self.moveItem = function(item,from,to){
        var idx=from.indexOf(item);
        if (idx != -1) {
            from.splice(idx, 1);
            to.push(item);
        }
    };

    self.verifyForm = function(){
        self.selectSessionObjectFromInputText();
        self.saveAction();
    };

    self.saveAction = function(){
        $http.put("api/sessions/"+self.sessionSelected.id+"/collaborators", self.selectedCollaboratorList).then(function(response){
            console.log(response);
            self.booleanVariable=true;
        });
    };

    self.selectSessionObjectFromInputText=function() {
        var selectedSessionSplittedArray = self.selectedSession.split(/ - | à /);
        self.trainingSessionObject={
            beginning: selectedSessionSplittedArray[1],
            ending: selectedSessionSplittedArray[2],
            location: selectedSessionSplittedArray[3],
            training:{
                trainingTitle:selectedSessionSplittedArray[0]
            }
        };
        console.log("self.trainingSessionObject: ",self.trainingSessionObject);
        return self.trainingSessionList.find(function(elem){
            if(elem.beginning===self.trainingSessionObject.beginning &&
               elem.ending===self.trainingSessionObject.ending &&
               elem.location===self.trainingSessionObject.location &&
               elem.training.trainingTitle===self.trainingSessionObject.training.trainingTitle){
               return elem;
            }
        });
    }
}])
    .config(['$routeProvider',function($routeProvider) {
        $routeProvider
           .when('/AffectTraining', {
                templateUrl: 'templates/affectTrainingSession.html',
                controller: 'controllerAffectTraining',
                controllerAs:'AS'
            })
    }
    ]);;