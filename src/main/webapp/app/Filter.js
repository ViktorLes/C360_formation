
//Filtre qui renvoie une sous liste d'objet 
angular.module('AppFilter').filter('searchByName',[function(){
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
}]);