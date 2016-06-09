angular.module('controllers').factory('InitBddService',['$http', function($http){
	var training={
			numberHalfDays:1,
			trainingTitle:"AngularJS" 
	};
	var session1={
			trainingDescription: training,
			beginning: "04/05/2016",
			ending: "06/05/2016",
			beginningTime: "08:00",
			endingTime: "08:00",
			location: "Salle Phuket"
	};
	var session2={
			trainingDescription: training,
			beginning: "07/05/2016",
			ending: "10/05/2016",
			beginningTime: "08:00",
			endingTime: "17:00",
			location: "Salle Bali"
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
			return $http.post("api/formations",training).then(function(data){
				training.id = 1;
				if(data.data)console.log("ajout training",training);
				return $http.post("api/collaborateurs",collaborator1);
			}).then(function(data){
				collaborator1.id = 2;
				if(data.data)console.log("ajout collaborator1",collaborator1);
				return $http.post("api/collaborateurs",collaborator2);
			}).then(function(data){
				collaborator2.id = 3;
				if(data.data)console.log("ajout collaborator2",collaborator2);
				return $http.post("api/sessions",session1);
			}).then(function(data){
				if(data.data)console.log("ajout session1",session1);
				return $http.post("api/sessions",session2);
			}).then(function(data){
				if(data.data)console.log("ajout session2",session2);
			});
		}
	};
}]);