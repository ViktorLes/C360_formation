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

    it('2) Demande de formation sans session planifié', function(){
        ctrl.requestedTrainingId = 6;
        ctrl.loadTrainingSessions();
        backend.expectGET('api/sessions/6').respond([]);
        backend.flush();
        expect(ctrl.isListEmpty).toBeTruthy();
        ctrl.verifyForm();
        backend.expectPOST('api/requests').respond({training: 6, collaborator: 4, trainingSessions:[]});
        backend.flush();
    });

    it(' Demande de formation sans selection au préalable', function() {
       // expect(ctrl.listTrainingSession).toEqual([]);
        /*
         ctrl.saveAction();

         backend.expectPOST('api/collaborateurs').respond('true');
         backend.flush();
         expect(ctrl.isNewPersonalIdNumber).toBeTruthy();
         //expect(loc.path()).toEqual('pageblanche.html');
         */
    });

    it('Demande de formation avec session', function () {
        /*
         ctrl.saveAction();

         backend.expectPOST('api/collaborateurs').respond('true');
         backend.flush();
         expect(ctrl.isNewPersonalIdNumber).toBeTruthy();
         //expect(loc.path()).toEqual('pageblanche.html');
         */
    });
});