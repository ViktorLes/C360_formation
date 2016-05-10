angular.module('GestForController').factory('InitBddService',['$http', function($http){
	var training={
			numberHalfDays:1,
			trainingTitle:"AngularJS" 
	};
	var session1={
			training: 1,
			beginning: "04/05/2016|08:00",
			ending: "06/05/2016|08:00", 
			location: "Salle Phuket"
	};
	var session2={
			training: 1,
			beginning: "07/05/2016|08:00",
			ending: "10/05/2016|08:00",
			location: "Salle Phuket"
	};
	var collaborator1={
		personnalIdNumber: "TLE",
		lastName: "Lecomte",
		firstName: "Thomas"
	};
	var collaborator2={
		personnalIdNumber: "NKA",
		lastName: "Kalmouni",
		firstName: "Nada"
	};
	
	return {
		init:function(){
			$http.post("api/formations",training).then(function(data){
				if(data.data) console.log("ajout training",training);
				return $http.post("api/sessions",session1);
			}).then(function(data){
				if(data.data)console.log("ajout session1",session1);
				return $http.post("api/sessions",session2);
			}).then(function(data){
				if(data.data)console.log("ajout session2",session2);
				return $http.post("api/collaborateurs",collaborator1);
			}).then(function(data){
				if(data.data)console.log("ajout collaborator1",collaborator1);
				return $http.post("api/collaborateurs",collaborator2);
			}).then(function(data){
				if(data.data)console.log("ajout collaborator2",collaborator2);
			});
		}
	};
}]);
