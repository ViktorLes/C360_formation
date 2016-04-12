
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

		//Controleur DeclarationSession
		GestForApp.controller('CtrlSes', ['$http',function($http) {
			var self = this;
			
			function pad2(number) {
				   return (number < 10 ? '0' : '') + number
				}
			
			var myTab =[];
				var debutH = 8; var finH = 18; var pas = 30; var finM =30; var debutM=0;
				var nbPasHeure = 60/pas;
				var nbPasHeures = (finH-debutH)*nbPasHeure;
				var nbPasMinutes = (finM-debutM)/pas;

				for(var compteur=0; compteur<(nbPasHeures+nbPasMinutes); compteur++)
					
					{
						myTab.push(pad2((debutH + Math.floor(compteur/nbPasHeure))).toString() + ":" + pad2((compteur%nbPasHeure*pas)).toString());
					}

				self.monTab = myTab;
		
				self.actionEnregistrer = function() {
				self.session.nomFormation= self.session.nomFormation.replace(/ +/g, " ");
				self.session.nomFormation= self.session.nomFormation.replace(/ +/g, " ");
				$http.post("api/sessions", self.formation).success(function(data){
			 		document.location.href = 'pageblancheSession.html';
				});
		    };
		}]);