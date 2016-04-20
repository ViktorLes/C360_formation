
//Module de L'GestForapp
var GestForApp = angular.module('GestForController', ['Datepicker']);
		
		//Controleur DeclarationFromation		
		GestForApp.controller('CtrlFor', ['$http',function($http) {
		
			var self = this;
						
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
		GestForApp.controller('CtrlCol', ['$http',function($http) {
			var self = this;
			
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
			self.isSessionAlreadyPlanned = true;
			self.hasCorrectDate = true;
			/*** Initialisation des données du formulaires **/
			$http.get("api/formations").then(function(data){
				self.formation = [];
				Array.prototype.push.apply(self.formation,data.data);
				self.SessionFormationId = self.formation[0].id;
			});

			self.d1 = datepicker.build();
			self.d2 = datepicker.build();

				function initHoraireTab(){
			
					function pad2(number) {
					   return (number < 10 ? '0' : '') + number;
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
				}
				initHoraireTab();
				self.heureDebut = self.monTab[0];
				self.heureFin = self.monTab[0];
				
				
				self.lieuFormation = 'Salle Phuket';
				
				/*** Enregistrement SessionFormation ***/
				self.actionEnregistrer = function() {
					var session = {
							formation: self.SessionFormationId,
							debut: $filter('date')(self.d1.dt,"dd/MM/yyyy") + "|" + self.heureDebut,
							fin:  $filter('date')(self.d2.dt,"dd/MM/yyyy") + "|" + self.heureFin,
							lieu: self.lieuFormation
					};
				
						$http.post("api/sessions", session).success(function(data){
							if(data == "true" || data == true) {
								self.hasCorrectDate = true;
								self.isSessionAlreadyPlanned = true;
								document.location.href = 'pageblanche.html';
							}else 
								{
								self.isSessionAlreadyPlanned = false;
								self.hasCorrectDate = false;
								}
						});
				}
		
		}]);

