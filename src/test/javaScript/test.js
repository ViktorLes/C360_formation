
//describe('first tests !', function () {
  //it('should works because 3 = 3', function () {
    //expect(4).toBe(4);
  //});

//});

describe('GF1', function() {
  var ctrl;
  var backend;
  var scope;
  var element;
  var  form;

  beforeEach(module('routeApp'));
  beforeEach(module('ngMock'));
  beforeEach(module('ngMockE2E'));

  beforeEach(inject(function ($controller, $httpBackend, $http, $templateCache){
    backend = $httpBackend;
   // backend.whenGET(/^templates\//).passThrough();

    ctrl = $controller('CtrlCol');

    /*
     *     var html = $templateCache.get('templates/EnregistrementCollaborateur.html');
    backend.flush();
    var template = angular.element(html);
    scope = template.scope();
    var linkFn = $compile(template);
    element = linkFn(scope);
    form = element.find("form");
     */
    
   //$http.get('templates/EnregistrementCollaborateur.html').then(function(html) {
    var html = $templateCache.get('templates/EnregistrementCollaborateur.html');
    //var template = angular.element($templateCache.get('templates/EnregistrementCollaborateur.html')[1]);
      scope = ctrl.scope;
      var linkFn = $compile(template);
      element = linkFn(scope);
      form = element.find("form");
    }));

 

  describe('Test Enregistrement',function () {
    beforeEach(function () {
      ctrl.collaborateur = {};
      ctrl.collaborateur.nom = "Henri";
      ctrl.collaborateur.prenom = "ddsfs";
      ctrl.collaborateur.matricule = "BB554";
    });



    it('Test Valide', function () {
      ctrl.actionEnregistrer();
      backend.expectPOST('api/collaborateurs').respond('true');
      backend.flush();
      expect(ctrl.isNewMatricule).toBeTruthy();
    });
    it('Test Invalide', function () {
      ctrl.actionEnregistrer();
      backend.expectPOST('api/collaborateurs').respond('false');
      backend.flush();
      expect(ctrl.isNewMatricule).toBeFalsy();
    });

  });
  
});


