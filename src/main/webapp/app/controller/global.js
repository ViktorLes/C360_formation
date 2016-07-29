angular.module('controllers').controller('globalController', ['InitBddService', 'MockConnexionService', '$http', 'currentUserService','$location', function (InitBddService, MockConnexionService, $http, currentUserService,$location) {
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

    self.disconnectUser = function () {
        currentUserService.disconnectCurrentUser();
        $location.url('/authentification')
    };

    self.displayCollaborator = function () {
        if (currentUserService.getUserToken()) self.isUserConnected = true;
        return currentUserService.getUserName();
    };

    self.selectCollaborator = function (myCollaborator) {
        MockConnexionService.select(myCollaborator);
        self.collaboratorConnected = MockConnexionService.getCollaboratorDescription();
    };

}]);