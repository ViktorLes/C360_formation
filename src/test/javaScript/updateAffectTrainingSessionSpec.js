describe('update Affectation session', function () {
    var ctrl;
    var backend;
    var sessionsList = JSON.parse('[{"id":4,"trainingDescription":{"id":1,"version":0,"trainingTitle":"AngularJS","numberHalfDays":1},"beginning":"04/05/2016","ending":"06/05/2016","beginningTime":"08:00","endingTime":"08:00","location":"Salle Phuket"},{"id":5,"trainingDescription":{"id":1,"version":0,"trainingTitle":"AngularJS","numberHalfDays":1},"beginning":"07/05/2016","ending":"10/05/2016","beginningTime":"08:00","endingTime":"17:00","location":"Salle Bali"},{"id":6,"trainingDescription":{"id":1,"version":0,"trainingTitle":"AngularJS","numberHalfDays":1},"beginning":"31/05/2016","ending":"31/05/2016","beginningTime":"08:00","endingTime":"08:30","location":"Salle Phuket"}]');

    var collabortorThomas = JSON.parse('{"id":2,"version":0,"personnalIdNumber":"TLE","lastName":"Lecomte","firstName":"Thomas"}');
    var collabortorNada = JSON.parse('{"id":3,"version":0,"personnalIdNumber":"NKA","lastName":"Kalmouni","firstName":"Nada"}');
    var collaboratorBayrek = JSON.parse('{"id":7,"version":0,"personnalIdNumber":"MBO","lastName":"MOKNI","firstName":"Bayrek"}');
    var collaboratorJihad = JSON.parse('{"id":10,"version":0,"personnalIdNumber":"JEL","lastName":"ELKADIR","firstName":"Jihad"}');
    var collaboratorRiheb = JSON.parse('{"id":11,"version":0,"personnalIdNumber":"RHA","lastName":"HAFI","firstName":"Riheb"}');
    var availableRequestingCollaboratorList=[collabortorThomas,collaboratorBayrek];
    var allAvailableCollaboratorList = [collabortorThomas, collaboratorBayrek,collaboratorJihad,collaboratorRiheb];
    var affectedCollaboratorList = [collabortorNada];
    var affectedCollaboratorListToBeSaved = JSON.parse(JSON.stringify(affectedCollaboratorList));
    affectedCollaboratorListToBeSaved.push(collabortorThomas, collabortorNada);

    beforeEach(module('App'));

    beforeEach(inject(function ($controller, $httpBackend, $filter) {
        ctrl = $controller('controllerUpdateAffectTraining');
        backend = $httpBackend;
        filter = $filter('searchByString');
        backend.expectGET('api/sessions').respond(sessionsList);
        backend.flush();
    }));

    afterEach(function () {
        backend.verifyNoOutstandingExpectation();
        backend.verifyNoOutstandingRequest();
    });

    it('1) l\'affichage des deux cadres avec la selection d\'une session', function(){
        ctrl.selectedSession = "AngularJS - 31/05/2016 à 31/05/2016 - Salle Phuket";
        backend.expectGET("api/sessions/6/collaboratorsnotaffected").respond(availableRequestingCollaboratorList);
        backend.expectGET("api/sessions/6/collaboratorsaffected").respond(affectedCollaboratorList);
        ctrl.loadNotAffectedAndAffectedCollaboratorsList();
        backend.flush();
        expect(ctrl.sessionSelected).toBeTruthy();
    });

    it("2)Test liste collaborateur quand la case est cochée",function () {
        ctrl.selectedSession = "AngularJS - 31/05/2016 à 31/05/2016 - Salle Phuket";
        backend.expectGET("api/sessions/6/collaboratorsnotaffected").respond(availableRequestingCollaboratorList);
        backend.expectGET("api/sessions/6/collaboratorsaffected").respond(affectedCollaboratorList);
        ctrl.loadNotAffectedAndAffectedCollaboratorsList();
        backend.flush();
        expect(JSON.stringify(ctrl.availableCollaboratorList)).toEqual(JSON.stringify(availableRequestingCollaboratorList));
    });

    it("3)Test liste collaborateur quand la case est décochée",function () {
        ctrl.selectedSession = "AngularJS - 31/05/2016 à 31/05/2016 - Salle Phuket";
        backend.expectGET("api/sessions/6/collaboratorsnotaffected").respond(availableRequestingCollaboratorList);
        backend.expectGET("api/sessions/6/collaboratorsaffected").respond(affectedCollaboratorList);
        ctrl.loadNotAffectedAndAffectedCollaboratorsList();
        backend.flush();
        backend.expectGET("api/sessions/6/collaboratorsnotaffected").respond(allAvailableCollaboratorList);
        ctrl.showRequests=false;
        ctrl.showRequestChanged();
        backend.flush();
        expect(JSON.stringify(ctrl.availableCollaboratorList)).toEqual(JSON.stringify(allAvailableCollaboratorList));
    });
});
