angular.module('GestForController')
    .controller('CtrlCol',['$http', '$location',function($http, $location) {
        var self = this;
        self.regex = {};

        /*** Recupération des regex **/
        $http.get("api/collaborateurs/regex").then(function(data){
            self.regex.personalIdNumber = new RegExp(data.data.personalIdNumber);
            self.regex.trainingTitle = new RegExp(data.data.trainingTitle);
            self.regex.firstName = new RegExp(data.data.firstName);
        });

        self.isNewPersonalIdNumber = "true";
        self.isFalseForm = false;

        self.verifierForm=function(collaboratorForm){
            if(collaboratorForm.$invalid == false){
                self.actionEnregistrer();
            }
            else{
                self.isFalseForm = true;
            }
        }

        self.actionEnregistrer = function() {

            //delete useless spaces between words 
            self.collaborator.trainingTitle= self.collaborator.trainingTitle.replace(/ +/g, " ");
            self.collaborator.firstName= self.collaborator.firstName.replace(/ +/g, " ");

            //post the form to the server
            $http.post("api/collaborateurs", self.collaborator).success(function(data){
                if(data == "true" || data == true) {
                    self.isNewPersonalIdNumber = true;
                    document.location.href = 'pageblanche.html';
                }
                else self.isNewPersonalIdNumber = false;
            });
        };
    }]);