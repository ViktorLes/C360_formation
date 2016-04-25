
//Module de L'GestForapp
var GestForApp = angular.module('GestForController', ['Datepicker','AppFilter']);

//************************************************************************************//
//***** NAME: Controller Affectation session
//***** Description: moveItem / moveAll / CtrlItemIsSelectedTOEnableOrDisableButton
//*****              CtrlItemIsSelectedTOEnableOrDisableButton / CtrlMoveAllTOEnableOrDisableButton
//************************************************************************************//
		GestForApp.controller('CtrlAffectationSession', function($scope) {

			$scope.moveItem = function(item,from,to){
				var idx=from.indexOf(item);
		        if (idx != -1) {
		            from.splice(idx, 1);
		            to.push(item);      
		        }
			};
			$scope.moveAll = function(from, to) {
		        if((to.length+from.length)<=10){
		        	angular.forEach(from, function(item) {
			            to.push(item);
			        });
			        from.length = 0;
		        }				
			};
			$scope.moveAllFromSelectedToDisponible = function(from, to) {
		        angular.forEach(from, function(item) {
		            to.push(item);
		        });
		        from.length = 0;
			};
		    $scope.CtrlSelectedItemTOEnableOrDisableButton = function(disponibleCollaborateur) {
		    	if(typeof(disponibleCollaborateur) == "undefined" || disponibleCollaborateur.length ==0){
		    		return false;
		    	}
		    	else
		    		return true;
		    };
		    $scope.CtrlMoveAllTOEnableOrDisableButton = function(listDesCollaborateurs) {
		    	if(listDesCollaborateurs.length==0){
		    		return true;
		    	}
		    	else
		    		return false;    	
		    };
		    $scope.CtrlMaxCollaborateurSelectionnee = function(listDesCollaborateursSelectionnee) {
		    	if(listDesCollaborateursSelectionnee.length==10){
		    		return true;
		    	}
		    	else
		    		return false;    	
		    };
  // A retirer après l'intégration du Back
    $scope.listDesCollaborateursDisponibles = [
        {'id': '1','firstName': 'Gym',  'name': 'SEBASTIEN'},
        {'id': '2','firstName': 'Lee', 'name': 'MARION'},
        {'id': '3','firstName': 'Belloum',  'name': 'YOUSSEF'},
        {'id': '4','firstName': 'Thomas', 'name': 'ROMAIN'},
        {'id': '5','firstName': 'Alssandro', 'name': 'LAURA'},
        {'id': '6','firstName': 'Aurelian', 'name': 'JULIEN'},
        {'id': '7','firstName': 'Karieene',  'name': 'MARIE'},
        {'id': '4','firstName': 'ee', 'name': 'ROMAIN'},
        {'id': '5','firstName': 'ddd', 'name': 'LAURA'},
        {'id': '7','firstName': 'Jihad',  'name': 'Elkadir'},
        {'id': '5','firstName': 'ddd', 'name': 'LAURA'},
        {'id': '7','firstName': 'Jihad',  'name': 'Elkadir'}
    ];

    $scope.listDesCollaborateursSelectionnes = [];
    
    $scope.listDesSessionsDisponibles = [
        {'id': '1','version': 'V1','name': 'JAVA','debut': '01/01/2001 00:00:00','fin':'01/01/2000 00:00:00','lieu':'Salle Amilcar'},
        {'id': '2','version': 'V2','name': '.Net','debut': '02/02/2002 00:00:00','fin':'01/01/2000 00:00:00','lieu':'Salle Didon'},
        {'id': '3','version': 'V3','name': 'AngularJS','debut': '03/03/2003 00:00:00','fin':'03/03/2003 00:00:00','lieu':'Salle Paris'}
    ];
});
//************************************************************************************//

		//Controleur DeclarationFromation		
		GestForApp.controller('CtrlFor', ['$http', '$location',function($http, $location) {
		
			var self = this;

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

