
//Module de L'GestForapp
		var GestForApp = angular.module('GestForController', []);
		//Controleur DeclarationFromation		
		GestForApp.controller('CtrlFor', ['$http', '$templateCache',function($http,$templateCache) {
			var self = this;
			self.regexTitredelaformation = "/^[a-zA-Z-'. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæ\u0153ÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝ\u0178Æ\u0152]+$/";
			self.regexnombredemijournee = "/^[0-9]+$/";

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

			console.log(self.singleSelect);

			self.regexMatricule = "/^[A-Z0-9]+$/";
			self.regexNom = "/^[a-zA-Z-'. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæ\u0153ÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝ\u0178Æ\u0152]+$/";
			self.regexPrenom = "/^[a-zA-Z-'. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæ\u0153ÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝ\u0178Æ\u0152]+$/";
			
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
		