angular.module('controllers').controller('globalController', ['InitBddService', 'MockConnexionService', '$http', function(InitBddService, MockConnexionService, $http){
    var self = this;
    
    self.initBase = function() {
        InitBddService.init();
        $http.get("api/collaborateurs").then(function(response){
            self.collaborators = response.data;
            self.collaboratorSelected = self.collaborators[0];
            self.collaboratorConnected = self.collaborators[0];
        });
    };
    
    self.displayCollaborator = function(collaborator) {
        if(collaborator) {
            return collaborator.firstName+' '+collaborator.lastName;
        }
    };
    
    self.selectCollaborator = function(myCollaborator){
        MockConnexionService.select(myCollaborator);
        self.collaboratorConnected = MockConnexionService.getCollaboratorDescription();
    };
    
}]);