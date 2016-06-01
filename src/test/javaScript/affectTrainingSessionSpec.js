describe('Affectation session', function () {
    var $http;
    var ctrl;
    var sessionsList = JSON.parse('[{"id":4,"training":{"id":1,"version":0,"trainingTitle":"AngularJS","numberHalfDays":1},"beginning":"04/05/2016","ending":"06/05/2016","beginningTime":"08:00","endingTime":"08:00","location":"Salle Phuket"},{"id":5,"training":{"id":1,"version":0,"trainingTitle":"AngularJS","numberHalfDays":1},"beginning":"07/05/2016","ending":"10/05/2016","beginningTime":"08:00","endingTime":"17:00","location":"Salle Bali"},{"id":6,"training":{"id":1,"version":0,"trainingTitle":"AngularJS","numberHalfDays":1},"beginning":"31/05/2016","ending":"31/05/2016","beginningTime":"08:00","endingTime":"08:30","location":"Salle Phuket"}]');
    var collaborator1 = {lastName: "JEAN", firstName: "Michel", personalIdNumber: "MJE3501"};
    var collaborator2 = {lastName: "HAFI", firstName: "Nada", personalIdNumber: "NHA3507"};
    var formList = [collaborator1, collaborator2];
    var toList = [];
    var selectedSession = {
        "id": 4,
        "training": {"id": 1, "version": 0, "trainingTitle": "AngularJS", "numberHalfDays": 1},
        "beginning": "04/05/2016",
        "ending": "06/05/2016",
        "beginningTime": "08:00",
        "endingTime": "08:00",
        "location": "Salle Phuket"
    };
    var collaboratorNotAffectedList = JSON.parse('[{"id":2,"version":0,"personnalIdNumber":"TLE","lastName":"Lecomte","firstName":"Thomas"},{"id":3,"version":0,"personnalIdNumber":"NKA","lastName":"Kalmouni","firstName":"Nada"}]');
    var collaboratorAffectedList = JSON.parse('[{"id":6,"version":0,"personnalIdNumber":"MBO","lastName":"MOKNI","firstName":"Bayrek"}]');

    beforeEach(module('App'));


    beforeEach(inject(function ($controller, $httpBackend) {
        $http = $httpBackend;
        ctrl = $controller('controllerAffectTraining');
        expect(ctrl.booleanVariable).toBeFalsy();
        expect(ctrl.boolErrNoSessionSelected).toBeFalsy();
        $http.expectGET('api/sessions').respond(sessionsList);
    }));

    it('1) Test load Affected and Not Affected Collaborators Lists', function () {
        expect(ctrl.noneSessionSelected).toBeFalsy();
        $http.expectGET("api/sessions/" + selectedSession.id + "/collaboratorsnotaffected").respond(collaboratorNotAffectedList);
        $http.expectGET("api/sessions/" + selectedSession.id + "/collaboratorsaffected").respond(collaboratorAffectedList);
    });

    it('2) Test Display Training Session', function () {
        expect(ctrl.displayTrainingSession(selectedSession)).toEqual(selectedSession.training.trainingTitle + ' - ' + selectedSession.beginning + ' à ' + selectedSession.ending + ' - ' + selectedSession.location);
    });

    it('3) Test déplacement d\'un collaborateur', function () {
        ctrl.moveItem(collaborator2, formList, toList);
        expect(toList).toContain(collaborator2);
        expect(formList).toContain(collaborator1);
    });

    it('4) Test select Session Object From Input Text', function () {
        ctrl.selectedSession = selectedSession.training.trainingTitle + ' - ' + selectedSession.beginning + ' à ' + selectedSession.ending + ' - ' + selectedSession.location;
        ctrl.trainingSessionList = sessionsList;
        expect(ctrl.selectSessionObjectFromInputText()).toEqual(selectedSession);
    });

    it('5) Test verifier form avec une session definie ', function () {
        ctrl.verifyForm();
        $http.expectPOST("api/sessions/" + selectedSession.id + "/collaborators", collaboratorAffectedList).respond('true');
    });

    it('6) Test verifier form avec une session indefinie ', function () {
        selectedSession = undefined;
        ctrl.verifyForm();
        expect(ctrl.boolErrNoSessionSelected).toBeTruthy();
    });
});