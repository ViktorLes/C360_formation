describe('Declaration Formation', function() {
    var ctrl;
    var backend;
    var loc;

    beforeEach(module('App'));

    beforeEach(inject(function($controller, $httpBackend, $location){
        backend = $httpBackend;
        loc = $location;
        ctrl = $controller('controllerRegisterTraining');

    }));

    describe('Test DeclarationFormation',function () {

        beforeEach(function () {
            backend.expectGET('api/formations/regex').respond('{"TRAINING_TITLE":"^[a-zA-Z0-9+#\'-. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ]+$","NUMBER_HALF_DAYS":"^[0-9]+$"}');
            ctrl.training = {};
            ctrl.training.trainingTitle = "Title";
            ctrl.training.numberHaldDays = "4";
        });

        afterEach(function(){
            backend.verifyNoOutstandingExpectation();
            backend.verifyNoOutstandingRequest();
        });


        it('Valide', function () {
            ctrl.saveAction();
            backend.expectPOST('api/formations').respond('true');
            backend.flush();
            expect(ctrl.isNewTrainingTitle).toBeTruthy();
            //expect(loc.path()).toEqual('pageblanche.html');
        });

        it('Invalide', function () {
            ctrl.saveAction();
            backend.expectPOST('api/formations').respond('false');
            backend.flush();
            expect(ctrl.isNewTrainingTitle).toBeFalsy();
            //expect(loc.path()).toEqual('#/EnregistrementCollaborateur');
        });

    });

});



