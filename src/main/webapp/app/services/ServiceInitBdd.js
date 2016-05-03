angular.module('GestForController').factory('InitBddService',[function(){
	var formation={
			numberHalfDays:"1",
			trainingTitle:"AngularJS" 
	};
	var session1={
			training: 1,
			debut: "04/05/2016|08:00",
			fin: "06/05/2016|08:00", 
			lieu: "Salle Phuket"
	};
	var session2={
			training: 1,
			debut: "07/05/2016|08:00",
			fin: "10/05/2016|08:00", 
			lieu: "Salle Phuket"
	};
	return {
		init:function($http){
			$http.post("api/formations",training).then(function(data){
				console.log("ajout training",training);
				
				$http.post("api/sessions",session1).then(function(data){
					console.log("ajout session1",session1);
					
					$http.post("api/sessions",session2).then(function(data){
						console.log("ajout session2",session2);
					});
				});
				
			});
		}
	};
}]);
