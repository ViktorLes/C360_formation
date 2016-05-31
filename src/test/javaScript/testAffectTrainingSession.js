describe('Affectation session', function () {
    var $http;
    var ctrl;
    var sessionsList = [{
        "id": 4,
        "training": {"id": 1, "version": 0, "trainingTitle": "AngularJS", "numberHalfDays": 1},
        "beginning": "04/05/2016",
        "ending": "06/05/2016",
        "beginningTime": "08:00",
        "endingTime": "08:00",
        "location": "Salle Phuket"
    },
        {
            "id": 5,
            "training": {"id": 1, "version": 0, "trainingTitle": "AngularJS", "numberHalfDays": 1},
            "beginning": "07/05/2016",
            "ending": "10/05/2016",
            "beginningTime": "08:00",
            "endingTime": "17:00",
            "location": "Salle Bali"
        }
    ];
    var collaborator1 = {lastName: "aaaa", firstName: "kkkk", personalIdNumber: "AA3501"};
    var collaborator2 = {lastName: "hafi", firstName: "nada", personalIdNumber: "NHA3507"};
    var formList = [collaborator1, collaborator2];
    var toList = [];


    beforeEach(module('App'));

    beforeEach(inject(function ($controller, $httpBackend) {
        $http = $httpBackend;
        $http.expectGET('api/sessions').respond(sessionsList);
        $http.flush();
        ctrl = $controller('controllerAffectTraining');
    }));

    describe('Test AffectationSession', function () {

        it('move Item', function () {
            ctrl.moveItem(collaborator2, formList, toList);
            expect(toList).toContain(collaborator2);
        });
    });

});