describe('registerTrainingSession', function () {
    var ctrl;
    var datePiker;
    var httpBackend;
    var filter;
    var TRAININGS = JSON.parse('[{"id":1,"version":0,"trainingTitle":"AngularJS","numberHalfDays":1}]');

    beforeEach(module('App'));

    beforeEach(inject(function ($controller, DatepickerService, $httpBackend, $filter) {
        datePiker = DatepickerService;
        httpBackend = $httpBackend;
        filter = $filter;
        ctrl = $controller('controllerRegisterTrainingSession');
        expect(ctrl.isSessionAlreadyPlanned).toBeFalsy();
        httpBackend.expectGET("api/formations").respond(TRAININGS);
        httpBackend.flush();
    }));

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it('1) Enregistrement session', function () {
        ctrl.training = TRAININGS[0];
        ctrl.d1.dt = new Date(2016, 5, 23);
        ctrl.d2.dt = new Date(2016, 5, 25);
        ctrl.beginningHour = "08:00";
        ctrl.endHour = "08:00";
        ctrl.trainingLocation = "Salle Phuket";
        var session = {
            training: ctrl.training,
            beginning: filter('date')(ctrl.d1.dt, "dd/MM/yyyy"),
            ending: filter('date')(ctrl.d2.dt, "dd/MM/yyyy"),
            beginningTime: ctrl.beginningHour,
            endingTime: ctrl.endHour,
            location: ctrl.trainingLocation
        };
        ctrl.verifyForm(false);
        httpBackend.expectPOST("api/sessions", session).respond(true);
        httpBackend.flush();
        expect(ctrl.isSessionAlreadyPlanned).toBeFalsy();
    });

    it('2) Enregistrement avec formulaire invalide', function () {
        expect(ctrl.isFalseForm).toBeFalsy();
        ctrl.verifyForm(true);
        expect(ctrl.isFalseForm).toBeTruthy();
    });

    it('3) Enregistrement avec session déjà planifiée', function () {
        ctrl.training = TRAININGS[0];
        ctrl.d1.dt = new Date(2016, 5, 23);
        ctrl.d2.dt = new Date(2016, 5, 25);
        ctrl.beginningHour = "08:00";
        ctrl.endHour = "08:00";
        ctrl.trainingLocation = "Salle Phuket";
        var session = {
            training: ctrl.training,
            beginning: filter('date')(ctrl.d1.dt, "dd/MM/yyyy"),
            ending: filter('date')(ctrl.d2.dt, "dd/MM/yyyy"),
            beginningTime: ctrl.beginningHour,
            endingTime: ctrl.endHour,
            location: ctrl.trainingLocation
        };
        ctrl.verifyForm(false);
        httpBackend.expectPOST("api/sessions", session).respond(false);
        httpBackend.flush();
        expect(ctrl.isSessionAlreadyPlanned).toBeTruthy();
    });
});