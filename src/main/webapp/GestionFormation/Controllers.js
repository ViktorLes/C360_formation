
//Module de L'GestForapp
		var GestForApp = angular.module('GestForController', ['Datepicker']);
		GestForApp.controller('CtrlFor', ['$http', '$timeout',function($http,$timeout) {
			var self = this;
						
			self.isNewTitleFormation = true;
			
			self.actionEnregistrer = function() {
				self.formation.titreformation= self.formation.titreformation.replace(/ +/g, " ");
				//self.formation.nombredemijournee= self.formation.nombredemijournee.replace(/ +/g, "");
				$http.post("api/formations", self.formation).success(function(data){		
					if(data == "true"){
						self.isNewTitleFormation = true;
				 		document.location.href = 'pageblancheformation.html';
					}
					else self.isNewTitleFormation = false;
				});
		    };
		}]);
		

		// Controleur EnregistrementCollab
		GestForApp.controller('CtrlCol', ['$http',function($http) {
			var self = this;
			
			self.isNewMatricule = "true";	
			self.actionEnregistrer = function() {
				
				//delete useless spaces between words 
				self.collaborateur.nom= self.collaborateur.nom.replace(/ +/g, " ");
				self.collaborateur.prenom= self.collaborateur.prenom.replace(/ +/g, " ");
				
				//post the form to the server
				$http.post("api/collaborateurs", self.collaborateur).success(function(data){
					 if(data == "true") {
						 self.isNewMatricule = true; 
						 document.location.href = 'pageblanche.html';
					 }
					 else self.isNewMatricule = false;
				});
		    };
		}]);
		
		GestForApp.controller('DatepickerDemoCtrl', ['DatepickerService', function(datepicker) {
			var self = this;
			self.d1 = datepicker.build();
			self.d2 = datepicker.build();
			
		}]);
		