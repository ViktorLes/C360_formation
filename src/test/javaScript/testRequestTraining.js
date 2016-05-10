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
    }));

    afterEach(function () {
        backend.verifyNoOutstandingExpectation();
        backend.verifyNoOutstandingRequest();
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