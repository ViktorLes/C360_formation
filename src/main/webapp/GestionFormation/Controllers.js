
//Module de L'GestForapp
		var GestForApp = angular.module('GestForController', []);
		//Controleur DeclarationFromation		
		GestForApp.controller('CtrlFor', ['$http',function($http) {
			var self = this;
			
			self.isNewTitleFormation = true;
			
			self.actionEnregistrer = function() {
				self.formation.titreformation= self.formation.titreformation.replace(/ +/g, " ");
				//self.formation.nombredemijournee= self.formation.nombredemijournee.replace(/ +/g, "");
				$http.post("api/formations", self.formation).success(function(data){
					console.log(data);
					self.isNewTitleFormation = data;
			 		if(self.isNewTitleFormation == true) document.location.href = 'pageblancheformation.html';
					
				});
		    };
		}]);
		

		// Controleur EnregistrementCollab
		GestForApp.controller('CtrlCol', ['$http',function($http) {
			var self = this;
			
			self.isNewMatricule = true;	
			self.actionEnregistrer = function() {
				
				//delete useless spaces between words 
				self.collaborateur.nom= self.collaborateur.nom.replace(/ +/g, " ");
				self.collaborateur.prenom= self.collaborateur.prenom.replace(/ +/g, " ");
				
				//post the form to the server
				$http.post("api/collaborateurs", self.collaborateur).success(function(data){
					//data = server return value : true or false,
					// (depends of the existence of a previous 'Matricule')
					 self.isNewMatricule = data; 
					if(self.isNewMatricule == true) document.location.href = 'pageblanche.html';
				});
		    };
		}]);
		