//************************************************************************************//
//***** NAME: Controller Affectation session
//***** Description: moveItem / moveAll / CtrlItemIsSelectedTOEnableOrDisableButton
//*****              CtrlItemIsSelectedTOEnableOrDisableButton / CtrlMoveAllTOEnableOrDisableButton
//************************************************************************************//
angular.module('controllers').controller('controllerAffectTraining', ['$http', '$location', '$filter', '$timeout', function ($http, $location, $filter, $timeout) {

    var self = this;
    self.isCollabaratorListUpdated = false;
    self.boolErrNoSessionSelected = false;

    //Récupérer la liste des sessions disponible
    $http.get("api/sessions").then(function (data) {
        self.trainingSessionList = data.data;
    });

<<<<<<< HEAD
    self.displayTrainingSession = function(mySession){
        return mySession.trainingDescription.trainingTitle+' - '+mySession.beginning+' à '+mySession.ending+' - '+mySession.location;
=======
    //Récupérer la liste des collaborateurs affectés et non affectés à la session
    self.loadNotAffectedCollaboratorsList = function () {
        self.availableCollaboratorList = [];
        self.selectedCollaboratorList = [];
        self.boolErrNoSessionSelected = false;
        self.sessionSelected = self.selectSessionObjectFromInputText();
        if (self.sessionSelected) {
            $http.get("api/sessions/" + self.sessionSelected.id + "/collaboratorsnotaffected").then(function (data) {
                self.availableCollaboratorList = data.data;
            });
            $http.get("api/sessions/" + self.sessionSelected.id + "/collaboratorsaffected").then(function (data) {
                self.selectedCollaboratorList = data.data;
            });
        }
    };

    self.displayTrainingSession = function (mySession) {
        return mySession.training.trainingTitle + ' - ' + mySession.beginning + ' à ' + mySession.ending + ' - ' + mySession.location;
>>>>>>> 5d51c637147120bde988d153a12ca2c9d2a1d1f9
    };

    //déplace d'une liste à une autre
    self.moveItem = function (item, from, to) {
        var idx = from.indexOf(item);
        if (idx != -1) {
            from.splice(idx, 1);
            to.push(item);
        }
    };

    self.verifyForm = function () {
        if (self.selectedSession !== undefined && self.selectedSession !== "") {
            if (self.sessionSelected) {
                self.saveAction();
                self.setConfirmationMessageTimOut();
            }
        }
        else {
            self.boolErrNoSessionSelected = true;
        }
    };

    self.saveAction = function () {
        $http.put("api/sessions/" + self.sessionSelected.id + "/collaborators", self.selectedCollaboratorList).then(function (response) {
            if(response.data) {
                self.isCollabaratorListUpdated = true; }
        });
    };

    self.setConfirmationMessageTimOut = function () {
        $timeout(function () {
            self.isCollabaratorListUpdated = false;
        }, 3000);
    };

    self.selectSessionObjectFromInputText = function () {
        var selectedSessionSplittedArray = self.selectedSession.split(/ - | à /);
        self.trainingSessionObject = {
            beginning: selectedSessionSplittedArray[1],
            ending: selectedSessionSplittedArray[2],
            location: selectedSessionSplittedArray[3],
<<<<<<< HEAD
            trainingDescription:{
                trainingTitle:selectedSessionSplittedArray[0]
            }
        };
        console.log("self.trainingSessionObject: ",self.trainingSessionObject);
        return self.trainingSessionList.find(function(elem){
            if(elem.beginning===self.trainingSessionObject.beginning &&
               elem.ending===self.trainingSessionObject.ending &&
               elem.location===self.trainingSessionObject.location &&
               elem.trainingDescription.trainingTitle===self.trainingSessionObject.trainingDescription.trainingTitle){
               return elem;
=======
            training: {
                trainingTitle: selectedSessionSplittedArray[0]
            }
        };

        return self.trainingSessionList.find(function (elem) {
            if (elem.beginning === self.trainingSessionObject.beginning &&
                elem.ending === self.trainingSessionObject.ending &&
                elem.location === self.trainingSessionObject.location &&
                elem.training.trainingTitle === self.trainingSessionObject.training.trainingTitle) {
                return elem;
>>>>>>> 5d51c637147120bde988d153a12ca2c9d2a1d1f9
            }
        });
    }
}])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/AffectTraining', {
                templateUrl: 'templates/affectTrainingSession.html',
                controller: 'controllerAffectTraining',
                controllerAs: 'AS'
            })
    }
    ]);