describe('Declaration Formation', function () {
    var ctrl;
    var backend;
    var loc;
    var form;
    var topic1 = JSON.parse('{"id":1,"name":"Développement Web"}');
    var topic2 = JSON.parse('{"id":2,"name":"Développement Mobile"}');
    var topicList = [topic1, topic2];
    var trainingList = JSON.parse('[{"id":3,"trainingTitle":"AngularJS","numberHalfDays":1,"topicDescription":{"id":1,"name":"Développement Web"}},{"id":4,"trainingTitle":"AAA","numberHalfDays":5,"topicDescription":{"id":1,"name":"Développement Web"}}]');

    function refreshFormAfterFillingField(form, inputName, error) {
        form.$error.required.splice(0,1);
        if(error) {
            form[inputName].$error = error;
            form[inputName].$invalid = true;
        } else {
            form[inputName].$error = {};
            form[inputName].$invalid = false;
        }
        if(!form.$error.required[0]) {
            form.$error = {};
        }
    }
    
    function expectFormToBeFilled(form) {
        expect(form.$error.required).toBeUndefined();
        form.$invalid = false;
    }
    
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
            $error: {required: [{}, {}, {}]}
        };
    }));

    describe('Test DeclarationFormation', function () {

        beforeEach(function () {
            backend.expectGET('api/formations/regex').respond('{"TRAINING_TITLE":"^[a-zA-Z0-9+#\'-. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ]+$","NUMBER_HALF_DAYS":"^[0-9]+$"}');
            backend.expectGET('api/themes').respond(topicList);
            backend.expectGET('api/formations').respond(trainingList);
            ctrl.training = {};
            backend.flush();
        });

        afterEach(function () {
            backend.verifyNoOutstandingExpectation();
            backend.verifyNoOutstandingRequest();
        });

        it('Valide', function () {
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, true)).toBeFalsy();
            ctrl.training.trainingTitle = "Title";
            expect(ctrl.training.trainingTitle).toMatch(ctrl.regex.trainingTitle);
            refreshFormAfterFillingField(form, "trainingTitle");
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, false)).toBeFalsy();
            ctrl.training.topicDescription = topic1;
            form.$error.required.splice(0,1);
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, true)).toBeFalsy();
            ctrl.training.numberHalfDays = "4";
            expect(ctrl.training.numberHalfDays).toMatch(ctrl.regex.numberHalfDays);
            refreshFormAfterFillingField(form, "numberHalfDays");
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, false)).toBeFalsy();
            expectFormToBeFilled(form);
            backend.expectPOST('api/formations').respond('true');
            ctrl.verifyForm(form);
            backend.flush();
            expect(ctrl.isNewTrainingTitle).toBeTruthy();
            expect(ctrl.isFalseForm).toBeFalsy();
            expect(ctrl.isThereAnEmptyField).toBeFalsy();
            expect(ctrl.isTrainingSaved).toBeTruthy();
            expect(loc.path()).toBe('/RegisterTraining');
        });

        it('Invalid because of training title already exists', function () {
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, true)).toBeFalsy();
            ctrl.training.trainingTitle = "Title";
            expect(ctrl.training.trainingTitle).toMatch(ctrl.regex.trainingTitle);
            refreshFormAfterFillingField(form, 'trainingTitle');
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, false)).toBeFalsy();
            ctrl.training.topicDescription = topic1;
            form.$error.required.splice(0,1);
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, true)).toBeFalsy();
            ctrl.training.numberHalfDays = "4";
            expect(ctrl.training.numberHalfDays).toMatch(ctrl.regex.numberHalfDays);
            refreshFormAfterFillingField(form, 'numberHalfDays');
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, false)).toBeFalsy();
            expectFormToBeFilled(form);
            backend.expectPOST('api/formations').respond('false');
            ctrl.verifyForm(form);
            backend.flush();
            expect(ctrl.isNewTrainingTitle).toBeFalsy();
            expect(ctrl.isFalseForm).toBeFalsy();
            expect(ctrl.isThereAnEmptyField).toBeFalsy();
            expect(ctrl.isTrainingSaved).toBeFalsy();
            expect(loc.path()).toBe('/RegisterTraining');
        });

        it('Invalid because of input avoid', function () {
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, true)).toBeFalsy();
            ctrl.training.trainingTitle = "Title@";
            expect(ctrl.training.trainingTitle).not.toMatch(ctrl.regex.trainingTitle);
            refreshFormAfterFillingField(form, 'trainingTitle', {pattern: true});
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, false)).toBeTruthy();
            ctrl.training.topicDescription = topic1;
            form.$error.required.splice(0,1);
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, true)).toBeFalsy();
            ctrl.training.numberHalfDays = "";
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, false)).toBeFalsy();
            expect(form.$error.required).toBeDefined();
            ctrl.verifyForm(form);
            expect(ctrl.isNewTrainingTitle).toBeTruthy();
            expect(ctrl.isFalseForm).toBeFalsy();
            expect(ctrl.isThereAnEmptyField).toBeTruthy();
            expect(ctrl.isTrainingSaved).toBeFalsy();
            expect(loc.path()).toBe('/RegisterTraining');
        });

        it('Invalid because of inputs incorrect', function () {
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, true)).toBeFalsy();
            ctrl.training.trainingTitle = "Title@";
            expect(ctrl.training.trainingTitle).not.toMatch(ctrl.regex.trainingTitle);
            refreshFormAfterFillingField(form, 'trainingTitle', {pattern: true});
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, false)).toBeTruthy();
            ctrl.training.topicDescription = topic1;
            form.$error.required.splice(0,1);
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, true)).toBeFalsy();
            ctrl.training.numberHalfDays = "@";
            expect(ctrl.training.numberHalfDays).not.toMatch(ctrl.regex.numberHalfDays);
            refreshFormAfterFillingField(form, 'numberHalfDays', {pattern: true});
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, false)).toBeTruthy();
            expect(form.$error.required).toBeUndefined();
            expect(form.$invalid).toBeTruthy();
            ctrl.verifyForm(form);
            expect(ctrl.isNewTrainingTitle).toBeTruthy();
            expect(ctrl.isFalseForm).toBeTruthy();
            expect(ctrl.isThereAnEmptyField).toBeFalsy();
            expect(ctrl.isTrainingSaved).toBeFalsy();
            expect(loc.path()).toBe('/RegisterTraining');
        });

        it('Au clic sur une formation, on est redirigé vers la page de gestion des sessions', function () {
            ctrl.manageSession();
            expect(loc.path()).toEqual("/manageSession");
        });
    });
});
