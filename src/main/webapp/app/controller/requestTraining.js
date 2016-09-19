angular.module('controllers')
    .controller('controllerRequestTraining', ['currentUserService', '$http', '$location', function (currentUserService, $http, $location) {
        var self = this;
        //Charge la liste de formations affiché dans le select box des formations
        $http.get("api/formations").then(function (data) {
            self.trainings = data.data;
        });

        //Charge la liste de sessions disponible en fonction de l'ID de training
        //selectionné grâce au 'select box' 
        self.loadTrainingSessions = function () {
            self.noneSessionSelected = false;
            self.hasToChooseOneTraining = false;
            self.alreadyRequestedSessions = {};
            self.listTrainingSession = [];
            if (Number.isInteger(self.requestedTraining.id)) {
                $http.get("api/formations/" + self.requestedTraining.id + "/alreadyrequestedsession/" + currentUserService.getCollaboratorIdentity().id)
                    .then(function (data) {
                        data.data.forEach(function (session) {console.log("alreadySelected");
                            self.alreadyRequestedSessions[session.id] = session; console.log("rq:"+session.id);
                        });
                        return $http.get("api/formations/" + self.requestedTraining.id + "/sessions");
                    },function (err) {console.log("err:" +err);})
                    .then(function (data) {console.log("all");
                        Array.prototype.push.apply(self.listTrainingSession, data.data);
                        if (self.listTrainingSession.length === 0) {
                            self.isListEmpty = true;
                        }
                        else self.isListEmpty = false;
                    });
            }
        };

        self.isDisabled = function (session) {
            return self.alreadyRequestedSessions [session.id];console.log("disabled:"+session.id);
        };

        self.verifyForm = function () {
            console.log("alreadyRequestedSessions", self.alreadyRequestedSessions);
            self.noneSessionSelected = false;
            self.hasToChooseOneTraining = false;
            if (self.requestedTraining) {
                if (typeof self.listTrainingSession !== 'undefined') {
                    if (self.isListEmpty) {
                        //Envoi au serveur une demande de session non programmée
                        self.saveAction();
                    } else if (self.listTrainingSession.some(function (elem) {
                            return elem.isChecked;
                        })) {
                        //Envoi 'des' sessions selectionné par le collaborateur au serveur
                        self.saveAction();
                    } else {
                        self.noneSessionSelected = true;
                    }
                }
            } else {
                self.hasToChooseOneTraining = true;
            }
        };

        self.saveAction = function () {
            var getSessionsSelected = function (listTrainingSession) {
                var listToSend = [];
                listTrainingSession.forEach(function (elem) {
                    if (elem.isChecked) listToSend.push(elem);
                });
                listToSend.forEach(function (elem) {
                    delete elem.isChecked;
                });
                return listToSend;
            };

            var myRequest = {
                trainingDescription: self.requestedTraining,
                collaboratorIdentity: currentUserService.getCollaboratorIdentity(),
                trainingSessionsDescriptions: getSessionsSelected(self.listTrainingSession)
            };

            $http.post("api/requests", myRequest).success(function (data) {
                if (data === true || data === "true") {
                    $location.url('/RequestTraining');
                }
                self.loadTrainingSessions();
            });
        }
    }])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/RequestTraining', {
                templateUrl: 'templates/requestTraining.html',
                controller: 'controllerRequestTraining',
                controllerAs: 'DmF'
            })
    }
    ]);