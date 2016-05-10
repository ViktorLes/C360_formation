angular.module('GestForController')
    .controller('CtrlCol',['$http', '$location',function($http, $location) {
        var self = this;
        self.regex = {};

        /*** Recupération des regex **/
        $http.get("api/collaborateurs/regex").then(function(data){
            self.regex.personalIdNumber = new RegExp(data.data.PERSONNAL_ID_NUMBER);
            self.regex.lastName = new RegExp(data.data.LAST_NAME);
            self.regex.firstName = new RegExp(data.data.FIRST_NAME);
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
            self.collaborator.lastName= self.collaborator.lastName.replace(/ +/g, " ");
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