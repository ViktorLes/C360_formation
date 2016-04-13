//describe('first tests !', function () {
  //it('should works because 3 = 3', function () {
    //expect(4).toBe(4);
  //});

//});

describe('GF1', function() {
  var ctrl;
  var backend;

  beforeEach(module('routeApp'));
  beforeEach(module('ngMock'));

  beforeEach(inject(function ($controller, $httpBackend){
    backend = $httpBackend;
    ctrl = $controller('CtrlCol');
  }));

  //beforeEach(module('ngMockE2E'));
  it('Test Enregistrement', function() {
    ctrl.collaborateur={};
    ctrl.collaborateur.nom="Henri";
    ctrl.collaborateur.prenom="ddsfs";
    ctrl.collaborateur.matricule="BB554";
    ctrl.actionEnregistrer();
    backend.expectPOST('api/collaborateurs').respond('true');
    backend.flush();
    expect(ctrl.isNewMatricule).toBeTruthy();
    ctrl.actionEnregistrer();
    backend.expectPOST('api/collaborateurs').respond('false');
    backend.flush();
    expect(ctrl.isNewMatricule).toBeFalsy();
    
  });
});

