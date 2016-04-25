describe('GF1', function() {
  var ctrl;
  var backend;
  var loc;

  beforeEach(module('App'));
 
  beforeEach(inject(function($controller, $httpBackend, $location){
    backend = $httpBackend;
    loc = $location;
    ctrl = $controller('CtrlCol');
    }));

  describe('Test EnregistrementCollaborateur',function () {

    beforeEach(function () {
      backend.expectGET('api/collaborateurs/regex').respond('{"matricule":"^[A-Z0-9]+$","nom":"^[a-zA-Z-\'. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ]+$","prenom":"^[a-zA-Z-\'. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ]+$"}');
      ctrl.collaborateur = {};
      ctrl.collaborateur.nom = "Henri";
      ctrl.collaborateur.prenom = "ddsfs";
      ctrl.collaborateur.matricule = "BB554";
    });

    afterEach(function(){
      backend.verifyNoOutstandingExpectation();
      backend.verifyNoOutstandingRequest();
    });
    
    
    it('Valide', function () {
      ctrl.actionEnregistrer();
      backend.expectPOST('api/collaborateurs').respond('true');
      backend.flush();
      expect(ctrl.isNewMatricule).toBeTruthy();
      //expect(loc.path()).toEqual('pageblanche.html');
    });

    it('Invalide', function () {
      ctrl.actionEnregistrer();
      backend.expectPOST('api/collaborateurs').respond('false');
      backend.flush();
      expect(ctrl.isNewMatricule).toBeFalsy();
      //expect(loc.path()).toEqual('#/EnregistrementCollaborateur');
    });

  });

});



