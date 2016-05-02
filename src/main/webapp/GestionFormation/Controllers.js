
//Module de L'GestForapp
var GestForApp = angular.module('GestForController', ['Datepicker','AppFilter']);

//************************************************************************************//
//***** NAME: Controller Affectation session
//***** Description: moveItem / moveAll / CtrlItemIsSelectedTOEnableOrDisableButton
//*****              CtrlItemIsSelectedTOEnableOrDisableButton / CtrlMoveAllTOEnableOrDisableButton
//************************************************************************************//
		GestForApp.controller('CtrlAffectationSession',['$scope','$http','$location','$filter',function($scope,$http, $location,$filter){
			
			var self = this;
			//Récupérer la liste des sessions disponible
			$http.get("api/sessions").then(function(data){
				self.SessionFormationList = [];
				Array.prototype.push.apply(self.SessionFormationList,data.data);

				function NouvelleSession(){
					var SessionConvertedList=[]; 
					for(var i=0 ; i<self.SessionFormationList.length ; i++){
						var SessionObjectConverted={
								nom: self.SessionFormationList[i].formation.titreformation,
								debut: $filter('date')(self.SessionFormationList[i].debut, 'dd/MM/yyyy'),
								fin: $filter('date')(self.SessionFormationList[i].fin, 'dd/MM/yyyy'),
								lieu: self.SessionFormationList[i].lieu
						}
						SessionConvertedList.push(SessionObjectConverted);	
					}
					return SessionConvertedList;
				};
				self.SessionFormationListConverted= [];
				Array.prototype.push.apply(self.SessionFormationListConverted,NouvelleSession());	
			});
			
			//moveItem d'une liste à une autre
			$scope.moveItem = function(item,from,to){
				var idx=from.indexOf(item);
		        if (idx != -1) {
		            from.splice(idx, 1);
		            to.push(item);         
		        }
			};
			self.moveAllFromSelectedToDisponible = function(from, to) {
		        angular.forEach(from, function(item) {
		            to.push(item);
		        });
		        from.length = 0;
			};
		    self.CtrlSelectedItemTOEnableOrDisableButton = function(disponibleCollaborateur) {
		    	if(typeof(disponibleCollaborateur) == "undefined" || disponibleCollaborateur.length ==0){
		    		return false;
		    	}
		    	else
		    		return true;
		    };
		    self.CtrlMoveAllTOEnableOrDisableButton = function(listDesCollaborateurs) {
		    	if(listDesCollaborateurs.length==0){
		    		return true;
		    	}
		    	else
		    		return false;    	
		    };
		    self.CtrlMaxCollaborateurSelectionnee = function(listDesCollaborateursSelectionnee) {
		    	if(listDesCollaborateursSelectionnee.length==10){
		    		return true;
		    	}
		    	else
		    		return false;    	
		    };
  
		  //Récupérer la liste des collaborateur disponible
			$http.get("api/collaborateurs").then(function(data){
				self.CollaborateurDisponibleList = [];
				self.SelectedCollaborateurList =[];
				Array.prototype.push.apply(self.CollaborateurDisponibleList,data.data);
				$scope.SelectedCollaborateur=self.CollaborateurDisponibleList[0];
			});	
}]);
//************************************************************************************//

		//Controleur DeclarationFromation		
		GestForApp.controller('CtrlFor', ['$http', '$location',function($http, $location) {
		
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
				 		document.location.href = 'pageblancheformation.html';
				 		//$location.path('pageblancheformation.html');
					}
					else {
						self.isNewTitleFormation = false;
					}
				});
		    };
		}]);
		

		// Controleur EnregistrementCollab
		GestForApp.controller('CtrlCol',['$http', '$location',function($http, $location) {
			var self = this;
			self.regex = {};

			/*** Recupération des regex **/
			$http.get("api/collaborateurs/regex").then(function(data){
				self.regex.matricule = new RegExp(data.data.matricule);
				self.regex.nom = new RegExp(data.data.nom);
				self.regex.prenom = new RegExp(data.data.prenom);
			});
			
			self.isNewMatricule = "true";
			self.isFalseForm = false;
			
			self.verifierForm=function(collaborateurForm){
				if(collaborateurForm.$invalid == false){
					self.actionEnregistrer();
				}
				else{
					self.isFalseForm = true;
				}
			}

			self.actionEnregistrer = function() {
				
				//delete useless spaces between words 
				self.collaborateur.nom= self.collaborateur.nom.replace(/ +/g, " ");
				self.collaborateur.prenom= self.collaborateur.prenom.replace(/ +/g, " ");
				
				//post the form to the server
				$http.post("api/collaborateurs", self.collaborateur).success(function(data){
					 if(data == "true" || data == true) {
						 self.isNewMatricule = true; 
						 document.location.href = 'pageblanche.html';
						 //$location.path('pageblanche.html');
					 }
					 else self.isNewMatricule = false;
				});
		    };
		}]);
		
		//Controleur DeclarationSession
		GestForApp.controller('CtrlSes', ['DatepickerService','$http','$filter',function(datepicker,$http,$filter) {
			var self = this;
			self.isSessionAlreadyPlanned = true;
			
			$http.get("api/formations").then(function(data){
				self.formation =[];
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
				self.isFalseForm = false;
				
				self.verifierForm=function(sessionForm){
					if(sessionForm.$invalid == false){
						self.actionEnregistrer();
					}
					else{
						self.isFalseForm = true;
					}
				}
				
			
				self.DateCorrect = function(heureDebut,heureFin) {
					if ((self.d1.dt < self.d2.dt)||(self.heureDebut < self.heureFin)){
						 return true;
					}
					else
						{
						return false;
						}
				}
				
				self.isWeekENDD2 = function(){
					return (self.d2.dt.getDay()== 0 || self.d2.dt.getDay()== 6);
				}
				
				self.isWeekENDD1 = function(){
					return (self.d1.dt.getDay()== 0 || self.d1.dt.getDay()== 6);
				}

				self.showForm = function(form){
					console.log("showForm >>>>>>>>>>>>>>>>>>>>>",form)
				}
				
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
								self.isSessionAlreadyPlanned = true;
								document.location.href = 'pageblanche.html';
							}else 
								{
								self.isSessionAlreadyPlanned = false;
								}
						});
				}
		
		}]);
		
		//controleur demande formation
		
		GestForApp.controller('CtrlDemandeForm',['$http', '$location','InitBddService',function($http, $location,InitBddService) {
			var self = this;
			//InitBddService.init($http);

			//Charge la liste de formations affiché dans le select box des formations
			$http.get("api/formations").then(function(data){
				self.formation = [];
				Array.prototype.push.apply(self.formation,data.data);
			});
			
			//Charge la liste de sessions disponible en fonction de l'ID de formation
			//selectionné grâce au 'select box' 
			self.loadSessionFormation=function() {
				self.noneSessionSelected = false;
				self.hasToChooseOneFormation = false;
				self.listSessionFormation = [];
				if (Number.isInteger(self.DemandeFormationId)) $http.get("api/sessions/" + self.DemandeFormationId).then(function (data) {
					Array.prototype.push.apply(self.listSessionFormation, data.data);
					if (self.listSessionFormation.length === 0) {
						self.isListEmpty = true;
					}
					else self.isListEmpty = false;
				});
			}
			
			self.verifierForm = function () {
				self.noneSessionSelected = false;
				self.hasToChooseOneFormation = false;
				if (Number.isInteger(self.DemandeFormationId)) {
					if (typeof self.listSessionFormation !== 'undefined' ) {
						if(self.isListEmpty){
							//envoi au serveur une demande de session non programmée
						}else if(self.listSessionFormation.some(function (elem) {return elem.isChecked;})){
							//envoi 'des' sessions selectionné par le collaborateur au serveur
						}else{
							self.noneSessionSelected = true;
						}
					}
				}else {
					self.hasToChooseOneFormation = true;
				}
			}
			
			self.actionEnregistrer = function(demande) {
								
			}
		   
		}]);