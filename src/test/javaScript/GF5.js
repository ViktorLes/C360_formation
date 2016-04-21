/*
describe('GF5', function() {
    var ctrl;
    var backend;
    var loc;

    beforeEach(module('App'));

    beforeEach(inject(function($controller, $httpBackend, $location){
        backend = $httpBackend;
        loc = $location;
        ctrl = $controller('CtrlSes');
    }));

    describe('Test DeclarationSessionFormation',function () {

        var formations = [
            {id: 1, titreformation:"Agile", nombredemijournee: 2, version: 0},
            {id: 2, titreformation:"Angular", nombredemijournee: 6, version: 0}
        ];

        beforeEach(function () {
            backend.expectGET('api/formations').respond(formations);
            expect(ctrl.formation).toEqual(formations);
        });

        afterEach(function(){
            backend.verifyNoOutstandingExpectation();
            backend.verifyNoOutstandingRequest();
        });


        it('Valide', function () {
            ctrl.actionEnregistrer();
            backend.expectPOST('api/sessions').respond('true');
            backend.flush();
            expect(ctrl.isSessionAlreadyPlanned).toBeTruthy();
            //expect(loc.path()).toEqual('pageblanche.html');
        });

        it('Invalide', function () {
            ctrl.actionEnregistrer();
            backend.expectPOST('api/sessions').respond('false');
            backend.flush();
            expect(ctrl.isSessionAlreadyPlanned).toBeFalsy();
            //expect(loc.path()).toEqual('#/EnregistrementCollaborateur');
        });

    });

});

*/

