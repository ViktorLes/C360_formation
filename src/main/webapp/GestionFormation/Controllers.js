
//Module de L'GestForapp
		var GestForApp = angular.module('GestForController', ['ui.bootstrap']); //'ui.bootstrap.datetimepicker'
		//Controleur DeclarationFromation		
		GestForApp.controller('CtrlFor', ['$http', '$timeout',function($http,$timeout) {
			var self = this;
			
			self.today = function() {
			    self.dt = new Date();
			  };
			  self.today();

			  self.showWeeks = true;
			  self.toggleWeeks = function () {
			    self.showWeeks = ! self.showWeeks;
			  };

			  self.clear = function () {
			    self.dt = null;
			  };

			  // Disable weekend selection
			  self.disabled = function(date, mode) {
			    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
			  };

			  self.toggleMin = function() {
			    self.minDate = ( self.minDate ) ? null : new Date();
			  };
			  self.toggleMin();

			  self.open = function() {
			    $timeout(function() {
			      self.opened = true;
			    });
			  };

			  self.dateOptions = {
			    'year-format': "'yy'",
			    'starting-day': 1
			  };
			
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
		
		GestForApp.controller('DatepickerDemoCtrl', function ($scope) {
			  $scope.today = function() {
				    $scope.dt = new Date();
				  };
				  $scope.today();

				  $scope.clear = function() {
				    $scope.dt = null;
				  };

				  $scope.inlineOptions = {
				    customClass: getDayClass,
				    minDate: new Date(),
				    showWeeks: true
				  };

				  $scope.dateOptions = {
				    dateDisabled: disabled,
				    formatYear: 'yy',
				    maxDate: new Date(2020, 5, 22),
				    minDate: new Date(),
				    startingDay: 1
				  };

				  // Disable weekend selection
				  function disabled(data) {
				    var date = data.date,
				      mode = data.mode;
				    return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
				  }

				  $scope.toggleMin = function() {
				    $scope.inlineOptions.minDate = $scope.inlineOptions.minDate ? null : new Date();
				    $scope.dateOptions.minDate = $scope.inlineOptions.minDate;
				  };

				  $scope.toggleMin();

				  $scope.open1 = function() {
				    $scope.popup1.opened = true;
				  };

				  $scope.open2 = function() {
				    $scope.popup2.opened = true;
				  };

				  $scope.setDate = function(year, month, day) {
				    $scope.dt = new Date(year, month, day);
				  };

				  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
				  $scope.format = $scope.formats[0];
				  $scope.altInputFormats = ['M!/d!/yyyy'];

				  $scope.popup1 = {
				    opened: false
				  };

				  $scope.popup2 = {
				    opened: false
				  };

				  var tomorrow = new Date();
				  tomorrow.setDate(tomorrow.getDate() + 1);
				  var afterTomorrow = new Date();
				  afterTomorrow.setDate(tomorrow.getDate() + 1);
				  $scope.events = [
				    {
				      date: tomorrow,
				      status: 'full'
				    },
				    {
				      date: afterTomorrow,
				      status: 'partially'
				    }
				  ];

				  function getDayClass(data) {
				    var date = data.date,
				      mode = data.mode;
				    if (mode === 'day') {
				      var dayToCheck = new Date(date).setHours(0,0,0,0);

				      for (var i = 0; i < $scope.events.length; i++) {
				        var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

				        if (dayToCheck === currentDay) {
				          return $scope.events[i].status;
				        }
				      }
				    }

				    return '';
				  }
				});
		