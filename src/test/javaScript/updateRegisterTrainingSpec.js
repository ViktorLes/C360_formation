describe('Declaration Formation', function () {
    var ctrl;
    var backend;
    var loc;
    var form;

    beforeEach(module('App'));

    beforeEach(inject(function ($controller, $httpBackend, $location) {
        backend = $httpBackend;
        loc = $location;
        loc.url('/RegisterTraining');
        ctrl = $controller('controllerRegisterTraining');
        form = {
            trainingTitle: {$invalid: true, $error: {required: true}},
            numberHalfDays: {$invalid: true, $error: {required: true}},
            $invalid: true,
            $error: {required: [{}, {}]}
        };
    }));

    describe('Test Declaration Formation', function () {

        beforeEach(function () {
            backend.expectGET('api/formations/regex').respond('{"TRAINING_TITLE":"^[a-zA-Z0-9+#\'-. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ]+$","NUMBER_HALF_DAYS":"^[0-9]+$"}');
            ctrl.training = {};
            backend.flush();
        });

        afterEach(function () {
            backend.verifyNoOutstandingExpectation();
            backend.verifyNoOutstandingRequest();
        });

        it('1) Valide', function () {
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, true)).toBeFalsy();
            ctrl.training.trainingTitle = "Title";
            expect(ctrl.training.trainingTitle).toMatch(ctrl.regex.trainingTitle);
            form.$error.required = [{}];
            form.trainingTitle.$error = {};
            form.trainingTitle.$invalid = false;
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, false)).toBeFalsy();
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, true)).toBeFalsy();
            ctrl.training.numberHalfDays = "4";
            expect(ctrl.training.numberHalfDays).toMatch(ctrl.regex.numberHalfDays);
            form.$error = {};
            form.numberHalfDays.$error = {};
            form.numberHalfDays.$invalid = false;
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, false)).toBeFalsy();
            form.$invalid = false;
            backend.expectPOST('api/formations').respond('true');
            ctrl.verifyForm(form);
            backend.flush();
            expect(ctrl.isNewTrainingTitle).toBeTruthy();
            expect(ctrl.isFalseForm).toBeFalsy();
            expect(ctrl.isThereAnEmptyField).toBeFalsy();
            expect(loc.path()).toBe('/pageblanche');
        });

        it('2) Invalid because of training title', function () {
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, true)).toBeFalsy();
            ctrl.training.trainingTitle = "Title";
            expect(ctrl.training.trainingTitle).toMatch(ctrl.regex.trainingTitle);
            form.$error.required = [{}];
            form.trainingTitle.$error = {};
            form.trainingTitle.$invalid = false;
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, false)).toBeFalsy();
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, true)).toBeFalsy();
            ctrl.training.numberHalfDays = "4";
            expect(ctrl.training.numberHalfDays).toMatch(ctrl.regex.numberHalfDays);
            form.$error = {};
            form.numberHalfDays.$error = {};
            form.numberHalfDays.$invalid = false;
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, false)).toBeFalsy();
            form.$invalid = false;
            backend.expectPOST('api/formations').respond('false');
            ctrl.verifyForm(form);
            backend.flush();
            expect(ctrl.isNewTrainingTitle).toBeFalsy();
            expect(ctrl.isFalseForm).toBeFalsy();
            expect(ctrl.isThereAnEmptyField).toBeFalsy();
            expect(loc.path()).toBe('/RegisterTraining');
        });

        it('3) Invalid because of input avoid', function () {
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, true)).toBeFalsy();
            ctrl.training.trainingTitle = "Title@";
            expect(ctrl.training.trainingTitle).not.toMatch(ctrl.regex.trainingTitle);
            form.$error.required = [{}];
            form.trainingTitle.$error = {pattern: true};
            form.trainingTitle.$invalid = true;
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, false)).toBeTruthy();
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, true)).toBeFalsy();
            ctrl.training.numberHalfDays = "";
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, false)).toBeFalsy();
            form.$invalid = true;
            ctrl.verifyForm(form);
            expect(ctrl.isNewTrainingTitle).toBeTruthy();
            expect(ctrl.isFalseForm).toBeFalsy();
            expect(ctrl.isThereAnEmptyField).toBeTruthy();
            expect(loc.path()).toBe('/RegisterTraining');
        });

        it('4) Invalid because of inputs incorrect', function () {
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, true)).toBeFalsy();
            ctrl.training.trainingTitle = "Title@";
            expect(ctrl.training.trainingTitle).not.toMatch(ctrl.regex.trainingTitle);
            form.$error.required = [{}];
            form.trainingTitle.$error = {pattern: true};
            form.trainingTitle.$invalid = true;
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, false)).toBeTruthy();
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, true)).toBeFalsy();
            ctrl.training.numberHalfDays = "@";
            expect(ctrl.training.numberHalfDays).not.toMatch(ctrl.regex.numberHalfDays);
            form.$error = {};
            form.numberHalfDays.$error = {pattern: true};
            form.numberHalfDays.$invalid = true;
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, false)).toBeTruthy();
            form.$invalid = true;
            ctrl.verifyForm(form);
            expect(ctrl.isNewTrainingTitle).toBeTruthy();
            expect(ctrl.isFalseForm).toBeTruthy();
            expect(ctrl.isThereAnEmptyField).toBeFalsy();
            expect(loc.path()).toBe('/RegisterTraining');
        });

        it("5) Au clic sur une formation, on est redirigé vers la page de gestion des sessions",function () {
            
        });

        it("6) Cliquer sur AJOUTER alors que l'un des champs est vide",function () {
            
        });
        
        it("7) Cliquer sur AJOUTER alors que l'un des champs est incorrect",function () {
            
        });
    });
});