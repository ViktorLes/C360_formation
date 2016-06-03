describe('Demande de Formation', function() {
    var ctrl;
    var backend;
    var loc;
    var trainings = JSON.parse('[{"id":1,"version":0,"trainingTitle":"AngularJS","numberHalfDays":1},{"id":6,"version":0,"trainingTitle":"Hibernate","numberHalfDays":5}]');
    var sessionsSelected = JSON.parse('[{"id":4,"training":{"id":1,"version":0,"trainingTitle":"AngularJS","numberHalfDays":1},"beginning":"04/05/2016","ending":"06/05/2016","beginningTime":"08:00","endingTime":"08:00","location":"Salle Phuket"}]');
    var sessionsFromTraining = JSON.parse('[{"id":4,"training":{"id":1,"version":0,"trainingTitle":"AngularJS","numberHalfDays":1},"beginning":"04/05/2016","ending":"06/05/2016","beginningTime":"08:00","endingTime":"08:00","location":"Salle Phuket"},{"id":5,"training":{"id":1,"version":0,"trainingTitle":"AngularJS","numberHalfDays":1},"beginning":"07/05/2016","ending":"10/05/2016","beginningTime":"08:00","endingTime":"08:00","location":"Salle Phuket"}]');

    beforeEach(module('App'));

    beforeEach(inject(function ($controller, $httpBackend, $location) {
        backend = $httpBackend;
        loc = $location;
        ctrl = $controller('controllerRequestTraining');
        backend.expectGET('api/formations').respond(trainings);
        backend.flush();
        expect(ctrl.noneSessionSelected).toBeFalsy();
        expect(ctrl.hasToChooseOneTraining).toBeFalsy();
        expect(ctrl.isListEmpty).toBeFalsy();
    }));

    afterEach(function () {
        backend.verifyNoOutstandingExpectation();
        backend.verifyNoOutstandingRequest();
    });

    it('1)liste de formation initialisée ', function () {
        expect(ctrl.trainings).toEqual(trainings);
    });

    it('2) Demande de formation sans sessions planifiées', function(){
        ctrl.requestedTraining = trainings[1];
        ctrl.loadTrainingSessions();
        backend.expectGET('api/formations/6/sessions').respond([]);
        backend.flush();
        expect(ctrl.isListEmpty).toBeTruthy();
        ctrl.verifyForm();
        backend.expectPOST('api/requests', {training: ctrl.requestedTraining, collaborator: 2, trainingSessions:[]}).respond(true);
        backend.flush();
    });

    it('3) Demande de formation avec sessions sélectionnées', function () {
        ctrl.requestedTraining = trainings[0];
        ctrl.loadTrainingSessions();
        backend.expectGET('api/formations/1/sessions').respond(sessionsFromTraining);
        backend.flush();
        expect(ctrl.isListEmpty).toBeFalsy();
        ctrl.listTrainingSession[0].isChecked = true;
        ctrl.verifyForm();
        backend.expectPOST('api/requests', {training: ctrl.requestedTraining, collaborator: 2, trainingSessions:sessionsSelected}).respond(true);
        backend.flush();
    });

    it('4) Demande de formation sans selection au préalable', function() {
        expect(ctrl.requestedTraining).toBeUndefined();
        expect(ctrl.listTrainingSession).toBeUndefined();
        ctrl.verifyForm();
        expect(ctrl.hasToChooseOneTraining).toBeTruthy();
    });

    it('5) Demande de formation sans sessions sélectionnées', function () {
        ctrl.requestedTraining = {id: 1};
        ctrl.loadTrainingSessions();
        backend.expectGET('api/formations/1/sessions').respond(sessionsFromTraining);
        backend.flush();
        expect(ctrl.isListEmpty).toBeFalsy();
        ctrl.verifyForm();
        expect(ctrl.noneSessionSelected).toBeTruthy();
    });
});