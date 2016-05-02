
angular.module('GestForController')
    .controller('CtrlFor', ['$http', '$location',function($http, $location) {

        var self = this;
        self.regex = {};

        /*** Recupération des regex **/
        $http.get("api/formations/regex").then(function(data){
            self.regex.titreformation = new RegExp(data.data.titreformation);
            self.regex.nombredemijournee = new RegExp(data.data.nombredemijournee);
        });

        self.isNewTitleFormation = true;
        self.isFalseForm = false;

        self.verifierForm=function(formationForm){
            if(formationForm.$invalid == false){
                self.actionEnregistrer();
            }
            else{
                self.isFalseForm = true;
            }
        }
        self.actionEnregistrer = function() {
            self.formation.titreformation= self.formation.titreformation.replace(/ +/g, " ");
            //self.formation.nombredemijournee= self.formation.nombredemijournee.replace(/ +/g, "");
            $http.post("api/formations", self.formation).success(function(data){
                if(data == "true" || data == true){
                    self.isNewTitleFormation = true;
                    document.location.href = '../../pageblancheformation.html';
                    //$location.path('pageblancheformation.html');
                }
                else {
                    self.isNewTitleFormation = false;
                }
            });
        };
    }]);