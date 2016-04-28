//Module de L'GestForapp
var AppFilter = angular.module('AppFilter', []);
		
//objectToString pour la recherche
AppFilter.filter('objectToString',[function(){
	return function(object,collaborateur){
		if(typeof(collaborateur) !== "undefined")
			{
					var listeObjetFiltre=[];
					for (var i=0;i<object.length;i++){					
						
						if(object[i].nom.toUpperCase().substr(0,collaborateur.length)== collaborateur.toUpperCase())
							{
							listeObjetFiltre.push(object[i]);
							}
					}
					return listeObjetFiltre;
			}
		return object;
	};
}])