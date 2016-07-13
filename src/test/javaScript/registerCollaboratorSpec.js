describe('Enregistrement Collaborateur', function () {
    var ctrl;
    var backend;
    var loc;
    var form;

    beforeEach(module('App'));

    beforeEach(inject(function ($location, $controller, $httpBackend) {
        backend = $httpBackend;
        loc = $location;
        loc.url('/RegisterCollaborator');
        ctrl = $controller('controllerRegisterCollaborator');
        form = {
            lastName: {$invalid: true, $error: {required: true}},
            firstName: {$invalid: true, $error: {required: true}},
            personnalIdNumber: {$invalid: true, $error: {required: true}},
            $invalid: true,
            $error: {required: [{}, {}, {}]}
        };
    }));

    describe('Test EnregistrementCollaborateur', function () {

        beforeEach(function () {
            backend.expectGET('api/collaborateurs/regex').respond('{"PERSONNAL_ID_NUMBER":"[A-Z]{3}[0-9]{4}","LAST_NAME":"^[a-zA-Z-\'. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ]+$","FIRST_NAME":"^[a-zA-Z-\'. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ]+$"}');
            ctrl.collaborator = {};
            backend.flush();
        });

        afterEach(function () {
            backend.verifyNoOutstandingExpectation();
            backend.verifyNoOutstandingRequest();
        });

        it('Valide', function () {
            expect(ctrl.isErrorInputMessageDisplayed(form.lastName, true)).toBeFalsy();
            ctrl.collaborator.lastName = "Darmet";
            expect(ctrl.collaborator.lastName).toMatch(ctrl.regex.lastName);
            form.$error.required = [{}, {}];
            form.lastName.$error = {};
            form.lastName.$invalid = false;
            expect(ctrl.isErrorInputMessageDisplayed(form.lastName, false)).toBeFalsy();
            expect(ctrl.isErrorInputMessageDisplayed(form.firstName, true)).toBeFalsy();
            ctrl.collaborator.firstName = "Henri";
            expect(ctrl.collaborator.firstName).toMatch(ctrl.regex.firstName);
            form.$error.required = [{}];
            form.firstName.$error = {};
            form.firstName.$invalid = false;
            expect(ctrl.isErrorInputMessageDisplayed(form.firstName, false)).toBeFalsy();
            expect(ctrl.isErrorInputMessageDisplayed(form.personnalIdNumber, true)).toBeFalsy();
            ctrl.collaborator.personnalIdNumber = "HDA1234";
            expect(ctrl.collaborator.personnalIdNumber).toMatch(ctrl.regex.personnalIdNumber);
            form.$error = {};
            form.personnalIdNumber.$error = {};
            form.personnalIdNumber.$invalid = false;
            expect(ctrl.isErrorInputMessageDisplayed(form.personnalIdNumber, false)).toBeFalsy();
            form.$invalid = false;
            backend.expectPOST('api/collaborateurs').respond('true');
            ctrl.verifyForm(form);
            backend.flush();
            expect(ctrl.isNewPersonalIdNumber).toBeTruthy();
            expect(ctrl.isFalseForm).toBeFalsy();
            expect(ctrl.isThereAnEmptyField).toBeFalsy();
            expect(loc.path()).toBe('/pageblanche');
        });

        it('Invalid because of matricule', function () {
            expect(ctrl.isErrorInputMessageDisplayed(form.lastName, true)).toBeFalsy();
            ctrl.collaborator.lastName = "Darmet";
            expect(ctrl.collaborator.lastName).toMatch(ctrl.regex.lastName);
            form.$error.required = [{}, {}];
            form.lastName.$error = {};
            form.lastName.$invalid = false;
            expect(ctrl.isErrorInputMessageDisplayed(form.lastName, false)).toBeFalsy();
            expect(ctrl.isErrorInputMessageDisplayed(form.firstName, true)).toBeFalsy();
            ctrl.collaborator.firstName = "Henri";
            expect(ctrl.collaborator.firstName).toMatch(ctrl.regex.firstName);
            form.$error.required = [{}];
            form.firstName.$error = {};
            form.firstName.$invalid = false;
            expect(ctrl.isErrorInputMessageDisplayed(form.firstName, false)).toBeFalsy();
            expect(ctrl.isErrorInputMessageDisplayed(form.personnalIdNumber, true)).toBeFalsy();
            ctrl.collaborator.personnalIdNumber = "HDA1234";
            expect(ctrl.collaborator.personnalIdNumber).toMatch(ctrl.regex.personnalIdNumber);
            form.$error = {};
            form.personnalIdNumber.$error = {};
            form.personnalIdNumber.$invalid = false;
            expect(ctrl.isErrorInputMessageDisplayed(form.personnalIdNumber, false)).toBeFalsy();
            form.$invalid = false;
            backend.expectPOST('api/collaborateurs').respond('false');
            ctrl.verifyForm(form);
            backend.flush();
            expect(ctrl.isNewPersonalIdNumber).toBeFalsy();
            expect(ctrl.isFalseForm).toBeFalsy();
            expect(ctrl.isThereAnEmptyField).toBeFalsy();
            expect(loc.path()).toBe('/RegisterCollaborator');
        });

        it('Invalid because of input avoid', function () {
            expect(ctrl.isErrorInputMessageDisplayed(form.lastName, true)).toBeFalsy();
            ctrl.collaborator.lastName = "Darmet@";
            expect(ctrl.collaborator.lastName).not.toMatch(ctrl.regex.lastName);
            form.$error.required = [{}, {}];
            form.lastName.$error = {pattern: true};
            form.lastName.$invalid = true;
            expect(ctrl.isErrorInputMessageDisplayed(form.lastName, false)).toBeTruthy();
            expect(ctrl.isErrorInputMessageDisplayed(form.firstName, true)).toBeFalsy();
            ctrl.collaborator.firstName = "";
            expect(ctrl.isErrorInputMessageDisplayed(form.firstName, false)).toBeFalsy();
            expect(ctrl.isErrorInputMessageDisplayed(form.personnalIdNumber, true)).toBeFalsy();
            ctrl.collaborator.personnalIdNumber = "HDA1234";
            expect(ctrl.collaborator.personnalIdNumber).toMatch(ctrl.regex.personnalIdNumber);
            form.personnalIdNumber.$error = {};
            form.personnalIdNumber.$invalid = false;
            expect(ctrl.isErrorInputMessageDisplayed(form.personnalIdNumber, false)).toBeFalsy();
            form.$invalid = true;
            ctrl.verifyForm(form);
            expect(ctrl.isNewPersonalIdNumber).toBeTruthy();
            expect(ctrl.isFalseForm).toBeFalsy();
            expect(ctrl.isThereAnEmptyField).toBeTruthy();
            expect(loc.path()).toBe('/RegisterCollaborator');
        });

        it('Invalid because of inputs incorrect', function () {
            expect(ctrl.isErrorInputMessageDisplayed(form.lastName, true)).toBeFalsy();
            ctrl.collaborator.lastName = "Darmet@";
            expect(ctrl.collaborator.lastName).not.toMatch(ctrl.regex.lastName);
            form.$error.required = [{}, {}];
            form.lastName.$error = {pattern: true};
            form.lastName.$invalid = true;
            expect(ctrl.isErrorInputMessageDisplayed(form.lastName, false)).toBeTruthy();
            expect(ctrl.isErrorInputMessageDisplayed(form.firstName, true)).toBeFalsy();
            ctrl.collaborator.firstName = "888";
            expect(ctrl.collaborator.firstName).not.toMatch(ctrl.regex.firstName);
            form.$error.required = [{}];
            form.firstName.$error = {pattern: true};
            form.firstName.$invalid = true;
            expect(ctrl.isErrorInputMessageDisplayed(form.firstName, false)).toBeTruthy();
            expect(ctrl.isErrorInputMessageDisplayed(form.personnalIdNumber, true)).toBeFalsy();
            ctrl.collaborator.personnalIdNumber = "HDA1234";
            expect(ctrl.collaborator.personnalIdNumber).toMatch(ctrl.regex.personnalIdNumber);
            form.$error = {};
            form.personnalIdNumber.$error = {};
            form.personnalIdNumber.$invalid = false;
            expect(ctrl.isErrorInputMessageDisplayed(form.personnalIdNumber, false)).toBeFalsy();
            form.$invalid = true;
            ctrl.verifyForm(form);
            expect(ctrl.isNewPersonalIdNumber).toBeTruthy();
            expect(ctrl.isFalseForm).toBeTruthy();
            expect(ctrl.isThereAnEmptyField).toBeFalsy();
            expect(loc.path()).toBe('/RegisterCollaborator');
        });
    });
});