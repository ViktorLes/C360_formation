describe('first tests !', function () {

  beforeEach(module('routeApp'));
  //beforeEach(module('ngMock'));

  var ctrl;
  var scope;
  var element;

  var form;

  var backend;


  beforeEach(inject(function($controller, $httpBackend, $compile) {
    backend = $httpBackend;
    ctrl = $controller('MainCtrl');

    var html = null;//get template in var template = ...;
    var template = angular.element(html);
    scope = template.scope();
    var linkFn = $compile(template);
    element = linkFn(scope);

    //search the form directive in 'element'

  }));

  it('should works because 3 = 3', function () {
    
    expect(4).toBe(4);
  });
});

