
angular.module('GestForController')
    .controller('CtrlFor', ['$http', '$location',function($http, $location) {

        var self = this;
        self.regex = {};

        /*** Recupération des regex **/
        $http.get("api/formations/regex").then(function(data){
            self.regex.trainingTitle = new RegExp(data.data.trainingTitle);
            self.regex.numberHalfDays = new RegExp(data.data.numberHalfDays);
        });

        self.isNewTrainingTitle = true;
        self.isFalseForm = false;

        self.verifierForm=function(trainingForm){
            if(trainingForm.$invalid == false){
                self.actionEnregistrer();
            }
            else{
                self.isFalseForm = true;
            }
        }
        self.actionEnregistrer = function() {
            self.training.trainingTitle= self.training.trainingTitle.replace(/ +/g, " ");
            //self.training.numberHalfDays= self.training.numberHalfDays.replace(/ +/g, "");
            $http.post("api/formations", self.training).success(function(data){
                if(data == "true" || data == true){
                    self.isNewTrainingTitle = true;
                    document.location.href = 'pageblancheformation.html';
                    //$location.path('pageblancheformation.html');
                }
                else {
                    self.isNewTrainingTitle = false;
                }
            });
        };
    }]);