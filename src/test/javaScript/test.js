describe('GF1', function() {
  var ctrl;
  var backend;

  beforeEach(module('App'));
  beforeEach(module('ngMock'));
  beforeEach(module('ngMockE2E'));
  beforeEach(module('ngAnimate'));
  beforeEach(module('ui.bootstrap'));
  beforeEach(module('Datepicker'));
 
  beforeEach(inject(function($controller, $httpBackend){
    backend = $httpBackend;
    ctrl = $controller('CtrlCol');
    }));

  describe('Test EnregistrementCollaborateur',function () {

    beforeEach(function () {
      ctrl.collaborateur = {};
      ctrl.collaborateur.nom = "Henri";
      ctrl.collaborateur.prenom = "ddsfs";
      ctrl.collaborateur.matricule = "BB554";
    });
    
    
    it('Valide', function () {
      ctrl.actionEnregistrer();
      backend.expectPOST('api/collaborateurs').respond('true');
      backend.flush();
      expect(ctrl.isNewMatricule).toBeTruthy();
    });

    it('Invalide', function () {
      ctrl.actionEnregistrer();
      backend.expectPOST('api/collaborateurs').respond('false');
      backend.flush();
      expect(ctrl.isNewMatricule).toBeFalsy();
    });

  });

});



