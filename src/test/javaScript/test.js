describe('first tests !', function () {

  beforeEach(module('routeApp'));
  //beforeEach(module('ngMock'));

  var ctrl;
  var scope;
  var element;

  var form;

  var backend;


  beforeEach(inject(function($controller, $httpBackend, $compile, $templateCache) {
    backend = $httpBackend;
    ctrl = $controller('MainCtrl');

    var html = $templateCache.get('templates/EnregistrementCollaborateur.html');
    backend.flush();
    var template = angular.element(html);
    scope = template.scope();
    var linkFn = $compile(template);
    element = linkFn(scope);
    form = element.find("form");
  }));

  it('should works because 3 = 3', function () {
    
    expect(4).toBe(4);
  });
  
  it('test template', function () {
	    
	   
  });
  
});

