describe('Demande de Formation', function() {
    var ctrl;
    var backend;
    var loc;

    beforeEach(module('App'));

    beforeEach(inject(function ($controller, $httpBackend, $location) {
        backend = $httpBackend;
        loc = $location;
        ctrl = $controller('controllerRequestTraining');
        backend.expectGET('api/formations').respond('[{"id":1,"version":0,"trainingTitle":"AngularJS","numberHalfDays":1},{"id":6,"version":0,"trainingTitle":"Hibernate","numberHalfDays":8}]');
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
        expect(ctrl.training).toEqual([{id:1,version:0,trainingTitle:"AngularJS",numberHalfDays:1},{id:6,version:0,trainingTitle:"Hibernate",numberHalfDays:8}]);
    });

    it('2) Demande de formation sans sessions planifiées', function(){
        ctrl.requestedTrainingId = 6;
        ctrl.loadTrainingSessions();
        backend.expectGET('api/formations/6/sessions').respond([]);
        backend.flush();
        expect(ctrl.isListEmpty).toBeTruthy();
        ctrl.verifyForm();
        backend.expectPOST('api/requests', {training: 6, collaborator: 4, trainingSessions:[]}).respond(true);
        backend.flush();
    });

    it('3) Demande de formation avec sessions sélectionnées', function () {
        ctrl.requestedTrainingId = 1;
        ctrl.loadTrainingSessions();
        backend.expectGET('api/formations/1/sessions').respond('[{"id":2,"version":0,"training":{"id":1,"version":0,"trainingTitle":"AngularJS","numberHalfDays":1},"beginning":1462341600000,"ending":1462514400000,"location":"Salle Phuket"},{"id":3,"version":0,"training":{"id":1,"version":0,"trainingTitle":"AngularJS","numberHalfDays":1},"beginning":1462600800000,"ending":1462860000000,"location":"Salle Phuket"}]');
        backend.flush();
        expect(ctrl.isListEmpty).toBeFalsy();
        ctrl.listTrainingSession[1].isChecked = true;
        ctrl.verifyForm();
        backend.expectPOST('api/requests', {training: 1, collaborator: 4, trainingSessions:[3]}).respond(true);
        backend.flush();
    });

    it('4) Demande de formation sans selection au préalable', function() {
        expect(ctrl.listTrainingSession).toBeUndefined();
        expect(ctrl.requestedTrainingId).toBeUndefined();
        ctrl.verifyForm();
        expect(ctrl.hasToChooseOneTraining).toBeTruthy();
    });

    it('5) Demande de formation sans sessions sélectionnées', function () {
        ctrl.requestedTrainingId = 1;
        ctrl.loadTrainingSessions();
        backend.expectGET('api/formations/1/sessions').respond('[{"id":2,"version":0,"training":{"id":1,"version":0,"trainingTitle":"AngularJS","numberHalfDays":1},"beginning":1462341600000,"ending":1462514400000,"location":"Salle Phuket"},{"id":3,"version":0,"training":{"id":1,"version":0,"trainingTitle":"AngularJS","numberHalfDays":1},"beginning":1462600800000,"ending":1462860000000,"location":"Salle Phuket"}]');
        backend.flush();
        expect(ctrl.isListEmpty).toBeFalsy();
        ctrl.verifyForm();
        expect(ctrl.noneSessionSelected).toBeTruthy();
    });
});