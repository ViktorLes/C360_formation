'use strict';	
//Module de L'GestForapp
		var GestForApp = angular.module('GestForController', []);
		//Controleur DeclarationFromation		
		GestForApp.controller('CtrlFor', ['$scope','$http',function($scope, $http) {
			var self = $scope;
			self.actionEnregistrer = function() {
				self.formation.titreformation= self.formation.titreformation.replace(/ +/g, " ");
				//self.formation.nombredemijournee= self.formation.nombredemijournee.replace(/ +/g, " ");
				$http.post("api/formations", self.formation).success(function(data){
			 		document.location.href = 'pageblancheformation.html';
					
				});
		    };
		}]);
		

		// Controleur EnregistrementCollab
		GestForApp.controller('CtrlCol', ['$scope','$http',function($scope, $http) {
			var self = $scope;
			
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
					 
					if(self.isNewMatricule) document.location.href = 'pageblanche.html';
				});
		    };
		}]);
		
		GestForApp.controller('cntrlTest', ['$scope',function($scope) {
			var self = $scope;
		}]);
	console.log("Ca marche!!!");