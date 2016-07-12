describe('registerTrainingSession', function () {
    var ctrl;
    var datePiker;
    var httpBackend;
    var filter;
    var TRAININGS = JSON.parse('[{"id":1,"version":0,"trainingTitle":"AngularJS","numberHalfDays":1}]');

    beforeEach(module('App'));

    beforeEach(inject(function ($controller, DatepickerService, $httpBackend, $filter, $location) {
        datePiker = DatepickerService;
        httpBackend = $httpBackend;
        filter = $filter;
        ctrl = $controller('controllerRegisterTrainingSession');
        loc = $location;
        loc.url('/RegisterTrainingSession');
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
        var dateBeginning=filter('date')(ctrl.d1.dt, "dd/MM/yyyy");
        expect(dateBeginning).toMatch(ctrl.regex.beginning);
        ctrl.d2.dt = new Date(2016, 5, 25);
        var dateEnding=filter('date')(ctrl.d2.dt, "dd/MM/yyyy");
        expect(dateEnding).toMatch(ctrl.regex.ending);
        ctrl.beginningHour = "08:00";
        ctrl.endHour = "08:00";
        var meetingRoom1 = {name: 'Salle Phuket'};
        ctrl.trainingLocation = meetingRoom1;

        var session = {
            trainingDescription: ctrl.training,
            beginning: dateBeginning,
            ending: dateEnding,
            beginningTime: ctrl.beginningHour,
            endingTime: ctrl.endHour,
            location: ctrl.trainingLocation.name
        };
        httpBackend.expectPOST("api/sessions", session).respond(true);
        ctrl.verifyForm(false);
        httpBackend.flush();
        expect(ctrl.isSessionAlreadyPlanned).toBeFalsy();
        expect(loc.path()).toBe('/pageblanche');
    });

    it('2) Enregistrement avec formulaire invalide', function () {
        expect(ctrl.isFalseForm).toBeFalsy();
        ctrl.verifyForm(true);
        expect(ctrl.isFalseForm).toBeTruthy();
        expect(loc.path()).toBe('/RegisterTrainingSession');
    });

    it('3 Enregistrement avec une date invalide',function () {
        ctrl.training = TRAININGS[0];
        ctrl.d1.dt = new Date(2016, 5, 23);
        ctrl.checkDateValide(ctrl.d1);
        var dateBeginning=filter('date')(ctrl.d1.dt, "dd/MM/yyyy");
        expect(dateBeginning).toMatch(ctrl.regex.beginning);
        var dateEnding="0304/2016";
        ctrl.d2.dt = undefined;
        ctrl.checkDateValide(ctrl.d2);
        expect(dateEnding).not.toMatch(ctrl.regex.ending);
        ctrl.beginningHour = "08:00";
        ctrl.endHour = "08:00";
        var meetingRoom1 = {name: 'Salle Phuket'};
        ctrl.trainingLocation = meetingRoom1;
        expect(ctrl.isFalseDate).toBeTruthy();
        expect(ctrl.isFalseForm).toBeFalsy();
    });

    it('4 Enregistrement avec session déjà planifiée', function () {
        ctrl.training = TRAININGS[0];
        ctrl.d1.dt = new Date(2016, 5, 23);
        var dateBeginning=filter('date')(ctrl.d1.dt, "dd/MM/yyyy");
        expect(dateBeginning).toMatch(ctrl.regex.beginning);
        ctrl.d2.dt = new Date(2016, 5, 25);
        var dateEnding=filter('date')(ctrl.d2.dt, "dd/MM/yyyy");
        expect(dateEnding).toMatch(ctrl.regex.ending);
        ctrl.beginningHour = "08:00";
        ctrl.endHour = "08:00";
        var meetingRoom1 = {name: 'Salle Phuket'};
        ctrl.trainingLocation = meetingRoom1;

        var session = {
            trainingDescription: ctrl.training,
            beginning: dateBeginning,
            ending: dateEnding,
            beginningTime: ctrl.beginningHour,
            endingTime: ctrl.endHour,
            location: ctrl.trainingLocation.name
        };
        httpBackend.expectPOST("api/sessions", session).respond(false);
        ctrl.verifyForm(false);
        httpBackend.flush();
        expect(ctrl.isSessionAlreadyPlanned).toBeTruthy();
        expect(loc.path()).toBe('/RegisterTrainingSession');
    });
});