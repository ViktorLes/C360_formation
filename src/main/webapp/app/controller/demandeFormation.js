angular.module('GestForController')
    .controller('CtrlDemandeForm',['$http', '$location','InitBddService',function($http, $location,InitBddService) {
        var self = this;
        //InitBddService.init($http);

        //Charge la liste de formations affiché dans le select box des formations
        $http.get("api/formations").then(function(data){
            self.training = [];
            Array.prototype.push.apply(self.training,data.data);
        });

        //Charge la liste de sessions disponible en fonction de l'ID de training
        //selectionné grâce au 'select box' 
        self.loadSessionFormation=function() {
            self.noneSessionSelected = false;
            self.hasToChooseOneFormation = false;
            self.listSessionFormation = [];
            if (Number.isInteger(self.DemandeFormationId)) $http.get("api/sessions/" + self.DemandeFormationId).then(function (data) {
                Array.prototype.push.apply(self.listSessionFormation, data.data);
                if (self.listSessionFormation.length === 0) {
                    self.isListEmpty = true;
                }
                else self.isListEmpty = false;
            });
        }

        self.verifierForm = function () {
            self.noneSessionSelected = false;
            self.hasToChooseOneFormation = false;
            if (Number.isInteger(self.DemandeFormationId)) {
                if (typeof self.listSessionFormation !== 'undefined' ) {
                    if(self.isListEmpty){
                        //envoi au serveur une demande de session non programmée
                    }else if(self.listSessionFormation.some(function (elem) {return elem.isChecked;})){
                        //envoi 'des' sessions selectionné par le collaborateur au serveur
                    }else{
                        self.noneSessionSelected = true;
                    }
                }
            }else {
                self.hasToChooseOneFormation = true;
            }
        }

        self.actionEnregistrer = function(demande) {

        }

    }]);