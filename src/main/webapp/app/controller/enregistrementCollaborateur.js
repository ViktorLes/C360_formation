angular.module('GestForController')
    .controller('CtrlCol',['$http', '$location',function($http, $location) {
        var self = this;
        self.regex = {};

        /*** Recupération des regex **/
        $http.get("api/collaborateurs/regex").then(function(data){
            self.regex.matricule = new RegExp(data.data.matricule);
            self.regex.nom = new RegExp(data.data.nom);
            self.regex.prenom = new RegExp(data.data.prenom);
        });

        self.isNewMatricule = "true";
        self.isFalseForm = false;

        self.verifierForm=function(collaborateurForm){
            if(collaborateurForm.$invalid == false){
                self.actionEnregistrer();
            }
            else{
                self.isFalseForm = true;
            }
        }

        self.actionEnregistrer = function() {

            //delete useless spaces between words 
            self.collaborateur.nom= self.collaborateur.nom.replace(/ +/g, " ");
            self.collaborateur.prenom= self.collaborateur.prenom.replace(/ +/g, " ");

            //post the form to the server
            $http.post("api/collaborateurs", self.collaborateur).success(function(data){
                if(data == "true" || data == true) {
                    self.isNewMatricule = true;
                    document.location.href = '../pageblanche.html';
                    //$location.path('pageblanche.html');
                }
                else self.isNewMatricule = false;
            });
        };
    }]);