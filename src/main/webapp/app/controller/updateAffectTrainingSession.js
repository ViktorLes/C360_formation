//************************************************************************************//
//***** NAME: Controller Affectation session
//***** Description: moveItem / moveAll / CtrlItemIsSelectedTOEnableOrDisableButton
//*****              CtrlItemIsSelectedTOEnableOrDisableButton / CtrlMoveAllTOEnableOrDisableButton
//************************************************************************************//
angular.module('controllers').controller('controllerUpdateAffectTraining', ['$http', '$location', '$filter', '$timeout', function ($http, $location, $filter, $timeout) {

    var self = this;
    self.isCollabaratorListUpdated = false;
    self.boolErrNoSessionSelected = false;
    self.showRequests = true;
    //Récupérer la liste des sessions disponible
    $http.get("api/sessions").then(function (data) {
        self.trainingSessionList = data.data;
    });

    self.updateCollaboratorAvailableByListIntersection = function () {
        for (var counterLeft = 0; counterLeft < self.availableCollaboratorList.length; counterLeft++) {
            for (var counterRight = 0; counterRight < self.selectedCollaboratorList.length; counterRight++) {
                console.log(self.selectedCollaboratorList[counterRight].id, self.availableCollaboratorList[counterLeft].id);
                if (self.selectedCollaboratorList[counterRight].id === self.availableCollaboratorList[counterLeft].id) {
                    self.availableCollaboratorList.splice(counterLeft, 1);
                }
            }
        }
    };

    self.showRequestChanged = function () {
        /******************************/
        var collaboratorThomas = JSON.parse('{"id":2,"version":0,"personnalIdNumber":"TLE","lastName":"Lecomte","firstName":"Thomas"}');
        var collaboratorNada = JSON.parse('{"id":3,"version":0,"personnalIdNumber":"NKA","lastName":"Kalmouni","firstName":"Nada"}');
        var collaboratorBayrek = JSON.parse('{"id":7,"version":0,"personnalIdNumber":"MBO","lastName":"MOKNI","firstName":"Bayrek"}');
        /******************************/
        self.availableCollaboratorList = [];
        if (!self.showRequests) {
            $http.get("api/sessions/" + self.sessionSelected.id + "/collaboratorsnotaffected").then(function (data) {
                self.availableCollaboratorList = data.data;
                self.updateCollaboratorAvailableByListIntersection();
            });
        }
        else {
            self.availableCollaboratorList = [collaboratorThomas, collaboratorBayrek];
            self.updateCollaboratorAvailableByListIntersection();
        }
    };
    //Récupérer la liste des collaborateurs affectés et non affectés à la session
    self.loadNotAffectedAndAffectedCollaboratorsList = function () {
        self.selectedCollaboratorList = [];
        self.boolErrNoSessionSelected = false;
        self.sessionSelected = self.selectSessionObjectFromInputText();
        if (self.sessionSelected) {
            self.showRequestChanged();
            $http.get("api/sessions/" + self.sessionSelected.id + "/collaboratorsaffected").then(function (data) {
                self.selectedCollaboratorList = data.data;
            });
        }
    };

    self.displayTrainingSession = function (mySession) {
        return mySession.trainingDescription.trainingTitle + ' - ' + mySession.beginning + ' à ' + mySession.ending + ' - ' + mySession.location;
    };

    //déplace d'une liste à une autre
    self.moveItem = function (item, from, to) {
        var actual = from.find(function (current) {
            return current.id === item.id;
        });

        if (actual) {
            from.splice(from.indexOf(actual), 1);
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
            if (response.data) {
                self.isCollabaratorListUpdated = true;
            }
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
            trainingDescription: {
                trainingTitle: selectedSessionSplittedArray[0]
            }
        };

        return self.trainingSessionList.find(function (elem) {
            if (elem.beginning === self.trainingSessionObject.beginning &&
                elem.ending === self.trainingSessionObject.ending &&
                elem.location === self.trainingSessionObject.location &&
                elem.trainingDescription.trainingTitle === self.trainingSessionObject.trainingDescription.trainingTitle) {
                return elem;
            }
        });
    }
}])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/updateAffectTraining', {
                templateUrl: 'templates/updateAffectTrainingSession.html',
                controller: 'controllerUpdateAffectTraining',
                controllerAs: 'AS'
            })
    }
    ]);