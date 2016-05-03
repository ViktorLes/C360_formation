describe('GF3', function() {
    var ctrl;
    var backend;
    var loc;

    beforeEach(module('App'));

    beforeEach(inject(function($controller, $httpBackend, $location){
        backend = $httpBackend;
        loc = $location;
        ctrl = $controller('CtrlFor');

    }));

    describe('Test DeclarationFormation',function () {

        beforeEach(function () {
            backend.expectGET('api/formations/regex').respond('{"nombredemijournee":"^[0-9]+$","titreformation":"^[a-zA-Z0-9+#\'-. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ]+$"}');
            ctrl.formation = {};
            ctrl.formation.titreformation = "Title";
            ctrl.formation.nombredemijournee = "4";
        });

        afterEach(function(){
            backend.verifyNoOutstandingExpectation();
            backend.verifyNoOutstandingRequest();
        });


        it('Valide', function () {
            ctrl.actionEnregistrer();
            backend.expectPOST('api/formations').respond('true');
            backend.flush();
            expect(ctrl.isNewTitleFormation).toBeTruthy();
            //expect(loc.path()).toEqual('pageblanche.html');
        });

        it('Invalide', function () {
            ctrl.actionEnregistrer();
            backend.expectPOST('api/formations').respond('false');
            backend.flush();
            expect(ctrl.isNewTitleFormation).toBeFalsy();
            //expect(loc.path()).toEqual('#/EnregistrementCollaborateur');
        });

    });

});



