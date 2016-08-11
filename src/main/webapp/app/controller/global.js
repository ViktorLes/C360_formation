angular.module('controllers').controller('globalController', ['InitBddService', 'MockConnexionService', 'TestConnectionService', '$http', 'currentUserService', '$location', function (InitBddService, MockConnexionService, TestConnectionService, $http, currentUserService, $location) {
    var self = this;

    TestConnectionService.init();

    self.initBase = function () {
        TestConnectionService.init();
        InitBddService.init();
        $http.get("api/collaborateurs").then(function (response) {
            self.collaborators = response.data;
            self.collaboratorSelected = self.collaborators[0];
            self.collaboratorConnected = self.collaborators[0];
        });
    };

    self.disconnectUser = function () {
        currentUserService.disconnectCurrentUser();
        $location.url('/');
    };

    self.displayCollaborator = function () {
        if (currentUserService.getUserLastName() != undefined) {
            return currentUserService.getUserFirstName() + ' ' + currentUserService.getUserLastName().toUpperCase();
        }
    };

    self.selectCollaborator = function (myCollaborator) {
        MockConnexionService.select(myCollaborator);
        self.collaboratorConnected = MockConnexionService.getCollaboratorDescription();
    };
    
    self.isUserConnected = function () {
        return currentUserService.isUserConnected();
    }

}]);