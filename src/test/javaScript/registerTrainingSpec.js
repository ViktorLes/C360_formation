describe('Declaration Formation', function () {
    var ctrl;
    var backend;
    var loc;
    var form;
    var topic1 = JSON.parse('{"id":1,"name":"Développement Web"}');
    var topic2 = JSON.parse('{"id":2,"name":"Développement Mobile"}');
    var topicList = [topic1, topic2];
    var trainingList = JSON.parse('[{"id":3,"trainingTitle":"AngularJS","numberHalfDays":1,"topicDescription":{"id":1,"name":"Développement Web"}},{"id":4,"trainingTitle":"AAA","numberHalfDays":5,"topicDescription":{"id":1,"name":"Développement Web"}}]');

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
            form.$error.required = [{}, {}];
            form.trainingTitle.$error = {};
            form.trainingTitle.$invalid = false;
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, false)).toBeFalsy();
            ctrl.training.topicDescription = topic1;
            form.$error.required = [{}];
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, true)).toBeFalsy();
            ctrl.training.numberHalfDays = "4";
            expect(ctrl.training.numberHalfDays).toMatch(ctrl.regex.numberHalfDays);
            form.$error = {};
            form.numberHalfDays.$error = {};
            form.numberHalfDays.$invalid = false;
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, false)).toBeFalsy();
            form.$invalid = false;
            backend.expectPOST('api/formations').respond(4);
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
            form.$error.required = [{}, {}];
            form.trainingTitle.$error = {};
            form.trainingTitle.$invalid = false;
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, false)).toBeFalsy();
            ctrl.training.topicDescription = topic1;
            form.$error.required = [{}];
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, true)).toBeFalsy();
            ctrl.training.numberHalfDays = "4";
            expect(ctrl.training.numberHalfDays).toMatch(ctrl.regex.numberHalfDays);
            form.$error = {};
            form.numberHalfDays.$error = {};
            form.numberHalfDays.$invalid = false;
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, false)).toBeFalsy();
            form.$invalid = false;
            backend.expectPOST('api/formations').respond(0);
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
            form.$error.required = [{}, {}];
            form.trainingTitle.$error = {pattern: true};
            form.trainingTitle.$invalid = true;
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, false)).toBeTruthy();
            ctrl.training.topicDescription = topic1;
            form.$error.required = [{}];
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, true)).toBeFalsy();
            ctrl.training.numberHalfDays = "";
            expect(ctrl.isErrorInputMessageDisplayed(form.numberHalfDays, false)).toBeFalsy();
            form.$invalid = true;
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
            form.$error.required = [{}, {}];
            form.trainingTitle.$error = {pattern: true};
            form.trainingTitle.$invalid = true;
            expect(ctrl.isErrorInputMessageDisplayed(form.trainingTitle, false)).toBeTruthy();
            ctrl.training.topicDescription = topic1;
            form.$error.required = [{}];
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
            expect(ctrl.isTrainingSaved).toBeFalsy();
            expect(loc.path()).toBe('/RegisterTraining');
        });

        it('Au clic sur une formation, on est redirigé vers la page de gestion des sessions', function () {
            ctrl.manageSession();
            expect(loc.path()).toEqual("/ManageSession");
        });
    });
});
