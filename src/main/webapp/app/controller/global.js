angular.module('controllers').controller('globalController', ['InitBddService', 'MockConnexionService', '$http', 'currentUserService', function (InitBddService, MockConnexionService, $http, currentUserService) {
    var self = this;
    self.isUserConnected = false;

    self.initBase = function () {
        InitBddService.init();
        $http.get("api/collaborateurs").then(function (response) {
            self.collaborators = response.data;
            self.collaboratorSelected = self.collaborators[0];
            self.collaboratorConnected = self.collaborators[0];
        });
    };

    self.displayCollaborator = function () {
        if(currentUserService.getUserToken()) self.isUserConnected = true;
            return currentUserService.getUserName();
    };

    self.selectCollaborator = function (myCollaborator) {
        MockConnexionService.select(myCollaborator);
        self.collaboratorConnected = MockConnexionService.getCollaboratorDescription();
    };

}]);