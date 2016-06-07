describe('Enregistrement Collaborateur', function () {
    var ctrl;
    var backend;
    var loc;

    beforeEach(module('App'));

    beforeEach(inject(function ($controller, $httpBackend, $location) {
        backend = $httpBackend;
        loc = $location;
        loc.url('/RegisterCollaborator');
        ctrl = $controller('controllerRegisterCollaborator');
    }));

    describe('Test EnregistrementCollaborateur', function () {

        beforeEach(function () {
            backend.expectGET('api/collaborateurs/regex').respond('{"PERSONNAL_ID_NUMBER":"^[A-Z0-9]+$","LAST_NAME":"^[a-zA-Z-\'. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ]+$","FIRST_NAME":"^[a-zA-Z-\'. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ]+$"}');
            ctrl.collaborator = {};
            ctrl.collaborator.lastName = "Darmet";
            ctrl.collaborator.firstName = "Henri";
            ctrl.collaborator.personnalIdnumber = "BB554";
            backend.flush();
        });

        afterEach(function () {
            backend.verifyNoOutstandingExpectation();
            backend.verifyNoOutstandingRequest();
        });

        it('Valide', function () {
            backend.expectPOST('api/collaborateurs').respond('true');
            ctrl.saveAction();
            backend.flush();
            expect(ctrl.isNewPersonalIdNumber).toBeTruthy();
            expect(loc.path()).toBe('/pageblanche');
        });

        it('Invalide', function () {
            backend.expectPOST('api/collaborateurs').respond('false');
            ctrl.saveAction();
            backend.flush();
            expect(ctrl.isNewPersonalIdNumber).toBeFalsy();
            expect(loc.path()).toBe('/RegisterCollaborator');
        });
    });
});