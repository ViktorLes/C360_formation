describe('Declaration Formation', function () {
    var ctrl;
    var backend;
    var loc;
    var form;
    var formHTML;
    var regex = JSON.parse('{"TRAINING_TITLE":"^[a-zA-Z0-9+#\'-. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ]+$","NUMBER_HALF_DAYS":"^[0-9]+$"}');
    var topic1 = JSON.parse('{"id":1,"name":"Développement Web"}');
    var topic2 = JSON.parse('{"id":2,"name":"Développement Mobile"}');
    var topicList = [topic1, topic2];
    var trainingList = JSON.parse('[{"id":3,"trainingTitle":"AngularJS","numberHalfDays":1,"topicDescription":{"id":1,"name":"Développement Web"}},{"id":4,"trainingTitle":"AAA","numberHalfDays":5,"topicDescription":{"id":1,"name":"Développement Web"}}]');

    beforeEach(module('App'));

    beforeEach(inject(function ($controller, $httpBackend, $location) {
        backend = $httpBackend;
        loc = $location;
        loc.url('/RegisterTraining');
        backend.expectGET('api/formations/regex').respond(regex);
        backend.expectGET('api/themes').respond(topicList);
        backend.expectGET('api/formations').respond(trainingList);
        ctrl = $controller('controllerRegisterTraining');
        backend.flush();
        ctrl.training = {};
        formHTML = new FormHTML;
        formHTML.createInputHTML({name: 'trainingTitle', model: ctrl.training, required: true, 'ng-pattern': ctrl.regex.trainingTitle});
        formHTML.createInputHTML({
            name: 'topicDescription',
            model: ctrl.training,
            type: 'select',
            required: true
        });
        formHTML.createInputHTML({
            name: 'numberHalfDays',
            model: ctrl.training,
            type: "number",
            required: true,
            min: 1,
            max: 200,
            'ng-pattern': ctrl.regex.numberHalfDays
        });
    }));

    afterEach(function () {
        backend.verifyNoOutstandingExpectation();
        backend.verifyNoOutstandingRequest();
    });

    function scenarioOfFormCorrectlyFilled(){
        expect(ctrl.isErrorInputMessageDisplayed(formHTML.form.trainingTitle, true)).toBeFalsy();
        formHTML.inputs['trainingTitle'].setValue("AngularJS");
        expect(ctrl.training.trainingTitle).toMatch(ctrl.regex.trainingTitle);
        expect(ctrl.isErrorInputMessageDisplayed(formHTML.form.trainingTitle, false)).toBeFalsy();
        formHTML.inputs['topicDescription'].setValue(topic1);
        expect(ctrl.isErrorInputMessageDisplayed(formHTML.form.numberHalfDays, true)).toBeFalsy();
        formHTML.inputs['numberHalfDays'].setValue("4");
        expect(ctrl.training.numberHalfDays).toMatch(ctrl.regex.numberHalfDays);
        expect(ctrl.isErrorInputMessageDisplayed(formHTML.form.numberHalfDays, false)).toBeFalsy();
        expectFormToBeComplete(formHTML.form);
        expectFormToBeValid(formHTML.form);
    }

    it('Valide', function () {
        scenarioOfFormCorrectlyFilled();
        backend.expectPOST('api/formations').respond("4");
        ctrl.verifyForm(formHTML.form);
        backend.flush();
        expect(ctrl.isNewTrainingTitle).toBeTruthy();
        expect(ctrl.isFalseForm).toBeFalsy();
        expect(ctrl.isThereAnEmptyField).toBeFalsy();
        expect(ctrl.isTrainingSaved).toBeTruthy();
        expect(loc.path()).toBe('/RegisterTraining');
    });

    it('Invalid because of training title already exists', function () {
        scenarioOfFormCorrectlyFilled();
        backend.expectPOST('api/formations').respond("0");
        ctrl.verifyForm(formHTML.form);
        backend.flush();
        expect(ctrl.isNewTrainingTitle).toBeFalsy();
        expect(ctrl.isFalseForm).toBeFalsy();
        expect(ctrl.isThereAnEmptyField).toBeFalsy();
        expect(ctrl.isTrainingSaved).toBeFalsy();
        expect(loc.path()).toBe('/RegisterTraining');
    });

    it('Invalid because of input avoid', function () {
        expect(ctrl.isErrorInputMessageDisplayed(formHTML.form.trainingTitle, true)).toBeFalsy();
        formHTML.inputs['trainingTitle'].setValue("");
        expect(ctrl.isErrorInputMessageDisplayed(formHTML.form.trainingTitle, false)).toBeFalsy();
        formHTML.inputs['topicDescription'].setValue(topic1);
        expect(ctrl.isErrorInputMessageDisplayed(formHTML.form.numberHalfDays, true)).toBeFalsy();
        formHTML.inputs['numberHalfDays'].setValue(null);
        expect(ctrl.training.numberHalfDays).not.toMatch(ctrl.regex.numberHalfDays);
        expect(ctrl.isErrorInputMessageDisplayed(formHTML.form.numberHalfDays, false)).toBeFalsy();
        expectFormToHaveMissingFields(formHTML.form, 2);
        expectFormToBeInvalid(formHTML.form);
        ctrl.verifyForm(formHTML.form);
        expect(ctrl.isNewTrainingTitle).toBeTruthy();
        expect(ctrl.isFalseForm).toBeFalsy();
        expect(ctrl.isThereAnEmptyField).toBeTruthy();
        expect(ctrl.isTrainingSaved).toBeFalsy();
        expect(loc.path()).toBe('/RegisterTraining');
    });

    it('Invalid because of inputs incorrect', function () {
        expect(ctrl.isErrorInputMessageDisplayed(formHTML.form.trainingTitle, true)).toBeFalsy();
        formHTML.inputs['trainingTitle'].setValue("AngularJS@");
        expect(ctrl.training.trainingTitle).not.toMatch(ctrl.regex.trainingTitle);
        expect(ctrl.isErrorInputMessageDisplayed(formHTML.form.trainingTitle, false)).toBeTruthy();
        formHTML.inputs['topicDescription'].setValue(topic1);
        expect(ctrl.isErrorInputMessageDisplayed(formHTML.form.numberHalfDays, true)).toBeFalsy();
        formHTML.inputs['numberHalfDays'].setValue("@");
        expect(ctrl.training.numberHalfDays).not.toMatch(ctrl.regex.numberHalfDays);
        expect(ctrl.isErrorInputMessageDisplayed(formHTML.form.numberHalfDays, false)).toBeTruthy();
        expectFormToBeComplete(formHTML.form);
        expectFormToBeInvalid(formHTML.form);
        expect(formHTML.form.$error.pattern).toEqual([{}, {}]);
        ctrl.verifyForm(formHTML.form);
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

