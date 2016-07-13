describe('Enregistrement Collaborateur', function () {
    var ctrl;
    var backend;
    var loc;
    var form;

    beforeEach(module('App'));

    beforeEach(inject(function ($controller, $httpBackend, $location) {
        backend = $httpBackend;
        loc = $location;
        loc.url('/RegisterCollaborator');
        ctrl = $controller('controllerRegisterCollaborator');
        form = {
            lastName: {$invalid: true, $error: {required: true}},
            firstName: {$invalid: true, $error: {required: true}},
            personnalIdNumber: {$invalid: true, $error: {required: true}},
            email: {$invalid: true, $error: {required: true}},
            password: {$invalid: true, $error: {required: true}},
            confirmPassword: {$invalid: true, $error: {required: true}},
            $invalid: true,
            $error: {required: [{}, {}, {}, {}, {}, {}]}
        };
    }));

    describe('Test EnregistrementCollaborateur', function () {

        beforeEach(function () {
            backend.expectGET('api/collaborateurs/regex').respond('{"PERSONNAL_ID_NUMBER":"[A-Z]{3}[0-9]{4}","LAST_NAME":"^[a-zA-Z-\'. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ]+$","FIRST_NAME":"^[a-zA-Z-\'. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ]+$", "EMAIL":"^[_a-z0-9]+(\\\\.[_a-z0-9]+)*@[a-z0-9-]+(\\\\.[a-z0-9-]+)*(\\\\.[a-z]{2,4})+$"}');
            ctrl.collaborator = {};
            backend.flush();
        });

        afterEach(function () {
            backend.verifyNoOutstandingExpectation();
            backend.verifyNoOutstandingRequest();
        });

        function fillEmailCorrectly(form){
            expect(ctrl.isErrorInputMessageDisplayed(form.email, true)).toBeFalsy();
            ctrl.collaborator.email = "henri.darmet@viseo.com";
            expect(ctrl.collaborator.email).toMatch(ctrl.regex.email);
            refreshFormAfterFillingField(form, 'email');
            expect(ctrl.isErrorInputMessageDisplayed(form.email, false)).toBeFalsy();
        }

        function fillPasswordCorrectly(form){
            expect(ctrl.isErrorInputMessageDisplayed(form.password, true)).toBeFalsy();
            ctrl.collaborator.password = "000000";
            refreshFormAfterFillingField(form, 'password');
            expect(ctrl.isErrorInputMessageDisplayed(form.password, false)).toBeFalsy();
            expect(ctrl.isErrorInputMessageDisplayed(form.confirmPassword, true)).toBeFalsy();
            ctrl.collaborator.confirmPassword = "000000";
            refreshFormAfterFillingField(form, 'confirmPassword');
            expect(ctrl.isErrorInputMessageDisplayed(form.confirmPassword, false)).toBeFalsy();
        }

        function fillIdentityCollaboratorCorrectly(form){
            expect(ctrl.isErrorInputMessageDisplayed(form.lastName, true)).toBeFalsy();
            ctrl.collaborator.lastName = "Darmet";
            expect(ctrl.collaborator.lastName).toMatch(ctrl.regex.lastName);
            refreshFormAfterFillingField(form, 'lastName');
            expect(ctrl.isErrorInputMessageDisplayed(form.lastName, false)).toBeFalsy();
            expect(ctrl.isErrorInputMessageDisplayed(form.firstName, true)).toBeFalsy();
            ctrl.collaborator.firstName = "Henri";
            expect(ctrl.collaborator.firstName).toMatch(ctrl.regex.firstName);
            refreshFormAfterFillingField(form, 'firstName');
            expect(ctrl.isErrorInputMessageDisplayed(form.firstName, false)).toBeFalsy();
            expect(ctrl.isErrorInputMessageDisplayed(form.personnalIdNumber, true)).toBeFalsy();
            ctrl.collaborator.personnalIdNumber = "HDA1234";
            expect(ctrl.collaborator.personnalIdNumber).toMatch(ctrl.regex.personnalIdNumber);
            refreshFormAfterFillingField(form, 'personnalIdNumber');
            expect(ctrl.isErrorInputMessageDisplayed(form.personnalIdNumber, false)).toBeFalsy();
        }

        function fillFormCorrectlyBeforeSubmit(){
            fillIdentityCollaboratorCorrectly(form);
            fillEmailCorrectly(form);
            fillPasswordCorrectly(form);
            expectFormToBeFilled(form);
        }

        it('1) Valide', function () {
            fillFormCorrectlyBeforeSubmit();
            backend.expectPOST('api/collaborateurs', self.collaborator).respond({response: "NotPersisted"});
            ctrl.verifyForm(form);
            backend.flush();
            expect(ctrl.isNewPersonalIdNumber).toBeTruthy();
            expect(ctrl.isFalseForm).toBeFalsy();
            expect(ctrl.isThereAnEmptyField).toBeFalsy();
            expect(loc.path()).toBe('/Authentication');
        });

        it('2) Invalid because of input avoid', function () {
            expect(ctrl.isErrorInputMessageDisplayed(form.lastName, true)).toBeFalsy();
            ctrl.collaborator.lastName = "Darmet@";
            expect(ctrl.collaborator.lastName).not.toMatch(ctrl.regex.lastName);
            refreshFormAfterFillingField(form, 'lastName', {pattern: true});
            expect(ctrl.isErrorInputMessageDisplayed(form.lastName, false)).toBeTruthy();
            expect(ctrl.isErrorInputMessageDisplayed(form.firstName, true)).toBeFalsy();
            ctrl.collaborator.firstName = "";
            expect(ctrl.isErrorInputMessageDisplayed(form.firstName, false)).toBeFalsy();
            expect(ctrl.isErrorInputMessageDisplayed(form.personnalIdNumber, true)).toBeFalsy();
            ctrl.collaborator.personnalIdNumber = "HDA1234";
            expect(ctrl.collaborator.personnalIdNumber).toMatch(ctrl.regex.personnalIdNumber);
            refreshFormAfterFillingField(form, 'personnalIdNumber');
            expect(ctrl.isErrorInputMessageDisplayed(form.personnalIdNumber, false)).toBeFalsy();
            fillEmailCorrectly(form);
            expect(ctrl.isErrorInputMessageDisplayed(form.password, true)).toBeFalsy();
            ctrl.collaborator.password = "000000";
            refreshFormAfterFillingField(form, 'password');
            expect(ctrl.isErrorInputMessageDisplayed(form.password, false)).toBeFalsy();
            expect(ctrl.isErrorInputMessageDisplayed(form.confirmPassword, true)).toBeFalsy();
            ctrl.collaborator.confirmPassword = "";
            expect(ctrl.isErrorInputMessageDisplayed(form.confirmPassword, false)).toBeFalsy();
            ctrl.verifyForm(form);
            expect(ctrl.isNewEmail).toBeTruthy();
            expect(ctrl.isNewPersonalIdNumber).toBeTruthy();
            expect(ctrl.isFalseForm).toBeFalsy();
            expect(ctrl.isThereAnEmptyField).toBeTruthy();
            expect(loc.path()).toBe('/RegisterCollaborator');
        });

        it('3) Password confirmation is invalid', function(){
            fillIdentityCollaboratorCorrectly(form);
            fillEmailCorrectly(form);
            expect(ctrl.isErrorInputMessageDisplayed(form.password, true)).toBeFalsy();
            ctrl.collaborator.password = "AAAAAAAAAAA";
            refreshFormAfterFillingField(form, 'password');
            expect(ctrl.isErrorInputMessageDisplayed(form.password, false)).toBeFalsy();
            expect(ctrl.isErrorInputMessageDisplayed(form.confirmPassword, true)).toBeFalsy();
            ctrl.collaborator.confirmPassword = "BBBBBBBBBBB";
            refreshFormAfterFillingField(form, 'confirmPassword', {pwCheck: true});
            expect(ctrl.isErrorInputMessageDisplayed(form.confirmPassword, false)).toBeTruthy();
            expect(form.$error.required).toBeUndefined();
            ctrl.verifyForm(form);
            expect(ctrl.isNewEmail).toBeTruthy();
            expect(ctrl.isNewPersonalIdNumber).toBeTruthy();
            expect(ctrl.isFalseForm).toBeTruthy();
            expect(ctrl.isThereAnEmptyField).toBeFalsy();
            expect(loc.path()).toBe('/RegisterCollaborator');
        });

        it('4) Invalid because of inputs incorrect', function () {
            expect(ctrl.isErrorInputMessageDisplayed(form.lastName, true)).toBeFalsy();
            ctrl.collaborator.lastName = "Darmet@";
            expect(ctrl.collaborator.lastName).not.toMatch(ctrl.regex.lastName);
            refreshFormAfterFillingField(form, 'lastName', {pattern: true});
            expect(ctrl.isErrorInputMessageDisplayed(form.lastName, false)).toBeTruthy();
            expect(ctrl.isErrorInputMessageDisplayed(form.firstName, true)).toBeFalsy();
            ctrl.collaborator.firstName = "888";
            expect(ctrl.collaborator.firstName).not.toMatch(ctrl.regex.firstName);
            refreshFormAfterFillingField(form, 'firstName', {pattern: true});
            expect(ctrl.isErrorInputMessageDisplayed(form.firstName, false)).toBeTruthy();
            expect(ctrl.isErrorInputMessageDisplayed(form.personnalIdNumber, true)).toBeFalsy();
            ctrl.collaborator.personnalIdNumber = "HDA1234";
            expect(ctrl.collaborator.personnalIdNumber).toMatch(ctrl.regex.personnalIdNumber);
            refreshFormAfterFillingField(form, 'personnalIdNumber');
            expect(ctrl.isErrorInputMessageDisplayed(form.personnalIdNumber, false)).toBeFalsy();
            fillEmailCorrectly(form);
            fillPasswordCorrectly(form);
            expect(form.$error.required).toBeUndefined();
            ctrl.verifyForm(form);
            expect(ctrl.isNewEmail).toBeTruthy();
            expect(ctrl.isNewPersonalIdNumber).toBeTruthy();
            expect(ctrl.isFalseForm).toBeTruthy();
            expect(ctrl.isThereAnEmptyField).toBeFalsy();
            expect(loc.path()).toBe('/RegisterCollaborator');
        });

        it('5) E-mail address is invalid', function(){
            fillIdentityCollaboratorCorrectly(form);
            expect(ctrl.isErrorInputMessageDisplayed(form.email, true)).toBeFalsy();
            ctrl.collaborator.email = "henri.darmet@viseocom";
            expect(ctrl.collaborator.email).not.toMatch(ctrl.regex.email);
            refreshFormAfterFillingField(form, 'email', {pattern: true});
            expect(ctrl.isErrorInputMessageDisplayed(form.email, false)).toBeTruthy();
            fillPasswordCorrectly(form);
            expect(form.$error.required).toBeUndefined();
            ctrl.verifyForm(form);
            expect(ctrl.isNewEmail).toBeTruthy();
            expect(ctrl.isNewPersonalIdNumber).toBeTruthy();
            expect(ctrl.isFalseForm).toBeTruthy();
            expect(ctrl.isThereAnEmptyField).toBeFalsy();
            expect(loc.path()).toBe('/RegisterCollaborator');
        });

        it('6) Invalid because e-mail is already used', function () {
            fillFormCorrectlyBeforeSubmit();
            backend.expectPOST('api/collaborateurs', self.collaborator).respond({response: "EmailPersisted"});
            ctrl.verifyForm(form);
            backend.flush();
            expect(ctrl.isNewEmail).toBeFalsy();
            expect(ctrl.isNewPersonalIdNumber).toBeTruthy();
            expect(ctrl.isFalseForm).toBeFalsy();
            expect(ctrl.isThereAnEmptyField).toBeFalsy();
            expect(loc.path()).toBe('/RegisterCollaborator');
        });

        it('7) Invalid because matricule is already used', function () {
            fillFormCorrectlyBeforeSubmit();
            backend.expectPOST('api/collaborateurs', self.collaborator).respond({response: "IdNumberPersisted"});
            ctrl.verifyForm(form);
            backend.flush();
            expect(ctrl.isNewEmail).toBeTruthy();
            expect(ctrl.isNewPersonalIdNumber).toBeFalsy();
            expect(ctrl.isFalseForm).toBeFalsy();
            expect(ctrl.isThereAnEmptyField).toBeFalsy();
            expect(loc.path()).toBe('/RegisterCollaborator');
        });

        it('8) Invalid because password is too short', function () {
            fillIdentityCollaboratorCorrectly(form);
            fillEmailCorrectly(form);
            expect(ctrl.isErrorInputMessageDisplayed(form.password, true)).toBeFalsy();
            ctrl.collaborator.password = "AAA";
            refreshFormAfterFillingField(form, 'password', {minlength: true});
            expect(ctrl.isErrorInputMessageDisplayed(form.password, false)).toBeTruthy();
            expect(ctrl.isErrorInputMessageDisplayed(form.confirmPassword, true)).toBeFalsy();
            ctrl.collaborator.confirmPassword = "AAA";
            refreshFormAfterFillingField(form, 'confirmPassword');
            expect(ctrl.isErrorInputMessageDisplayed(form.confirmPassword, false)).toBeFalsy();
            expect(form.$error.required).toBeUndefined();
            ctrl.verifyForm(form);
            expect(ctrl.isNewEmail).toBeTruthy();
            expect(ctrl.isNewPersonalIdNumber).toBeTruthy();
            expect(ctrl.isFalseForm).toBeTruthy();
            expect(ctrl.isThereAnEmptyField).toBeFalsy();
            expect(loc.path()).toBe('/RegisterCollaborator');
        });
    });
});