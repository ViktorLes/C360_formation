
//Module de L'GestForapp
var GestForApp = angular.module('GestForController', ['Datepicker']);
		
		//Controleur DeclarationFromation		
<<<<<<< HEAD
		GestForApp.controller('CtrlFor', ['$http', '$scope',function($http, $scope) {
			var self = this;
			//self.scope=$scope;
			self.regexTitredelaformation = "/^[a-zA-Z-'. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæ\u0153ÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝ\u0178Æ\u0152]+$/";
			self.regexnombredemijournee = "/^[0-9]+$/";
			
=======
		GestForApp.controller('CtrlFor', ['$http',function($http) {
		
			var self = this;
						
>>>>>>> b4169f785023fbd5e0ee4a48795428f916d53cb9
			self.isNewTitleFormation = true;

			self.actionEnregistrer = function() {
				self.formation.titreformation= self.formation.titreformation.replace(/ +/g, " ");
				//self.formation.nombredemijournee= self.formation.nombredemijournee.replace(/ +/g, "");
				$http.post("api/formations", self.formation).success(function(data){		
					if(data == "true" || data == true){
						self.isNewTitleFormation = true;
				 		document.location.href = 'pageblancheformation.html';
					}
					else {
						self.isNewTitleFormation = false;
					}
				});
		    };
		}]);
		

		// Controleur EnregistrementCollab
		GestForApp.controller('CtrlCol',['$http','$compile',function($http,$compile) {
			var self = this;
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
					 if(data == "true" || data == true) {
						 self.isNewMatricule = true; 
						 document.location.href = 'pageblanche.html';
					 }
					 else self.isNewMatricule = false;
				});
		    };
		}]);

		//Controleur DeclarationSession
		GestForApp.controller('CtrlSes', ['DatepickerService','$http','$filter',function(datepicker,$http,$filter) {
			var self = this;
			
			//var formation;
			$http.get("api/formations").then(function(data){
				//console.log(data)
				self.formation = [];
				Array.prototype.push.apply(self.formation,data.data)
			},
			function(){
				console.log("erreur!!")
			});

				self.d1 = datepicker.build();
				self.d2 = datepicker.build();
				
				
				
				self.actionEnregistrer = function() {
					var session = {
							formation: self.SessionFormation.id,
							debut: $filter('date')(self.d1.dt,"dd/MM/yyyy") + "|" + self.heureDebut,
							fin:  $filter('date')(self.d2.dt,"dd/MM/yyyy") + "|" + self.heureFin,
							lieu: self.lieuFormation
					};
				
					console.log("ma formation est: ",session);
					
						$http.post("api/sessions", self.session).success(function(data){
								 if(data == "true" || data == true) {
									 document.location.href = 'pageblanche.html';
								 }
						});
				}
			
			
			
			console.log("test DS");
			
			// Horaire
			function pad2(number) {
				   return (number < 10 ? '0' : '') + number
				}
			
			var myTab =[];
				var debutH=8; var finH=18; var pas=30; var finM=30; var debutM=0;
				var nbPasHeure = 60/pas;
				var nbPasHeures = (finH-debutH)*nbPasHeure;
				var nbPasMinutes = (finM-debutM)/pas;

				for(var compteur=0; compteur<(nbPasHeures+nbPasMinutes); compteur++)
					{
						myTab.push(pad2((debutH + Math.floor(compteur/nbPasHeure))).toString() + ":" + pad2((compteur%nbPasHeure*pas)).toString());
					}

				self.monTab = myTab;
		
				}]);

