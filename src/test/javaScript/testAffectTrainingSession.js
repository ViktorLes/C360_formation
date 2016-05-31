describe('Affectation session', function () {
    var $http;
    var ctrl;
    var collaborator1={lastName:"aaaa",firstName:"kkkk",personalIdNumber:"AA3501"};
    var collaborator2={lastName:"hafi",firstName:"nada",personalIdNumber:"NHA3507"};
    var formList=[collaborator1,collaborator2];
    var toList=[];


    beforeEach(module('App'));

    beforeEach(inject(function ($controller, $httpBackend) {
        $http = $httpBackend;
        ctrl = $controller('controllerAffectTraining');
    }));

   describe('Test AffectationSession', function () {

      it('move Item',function () {
          ctrl.moveItem(collaborator2,formList,toList);
          expect(toList).toContain(collaborator2);
      }) ;
    });

});