angular.module('controllers').factory('MockConnexionService',[function() {
    var collaboratorSelected;
    return {
        select: function(myCollaborator){
            if(myCollaborator) {
                collaboratorSelected = myCollaborator;
            }
        },
        getCollaboratorConnected: function () {
            return collaboratorSelected;
        }
    };
}]);