angular.module('controllers').factory('InitBddService', ['$http','hash', function ($http,hash) {
    var topic1 = {name: "Développement Web"};
    var topic2 = {name: "Développement Mobile"};
    var training = {
        numberHalfDays: 1,
        trainingTitle: "AngularJS",
        topicDescription: topic1
    };
    var session1 = {
        trainingDescription: training,
        beginning: "04/05/2016",
        ending: "06/05/2016",
        beginningTime: "08:00",
        endingTime: "08:00",
        location: "Salle Phuket"
    };
    var session2 = {
        trainingDescription: training,
        beginning: "07/05/2016",
        ending: "10/05/2016",
        beginningTime: "08:00",
        endingTime: "17:00",
        location: "Salle Bali"
    };
    var collaborator1 = {
        personnalIdNumber: "TLE3467",
        lastName: "Lecomte",
        firstName: "Thomas",
        email:"Thomas.lecomte@yahoo.fr",
        password:"thomas",
        isAdmin: false
    };
    var collaborator2 = {
        personnalIdNumber: "NKA7896",
        lastName: "Kalmouni",
        firstName: "Nada",
        email:"Nada.kalmouni@yahoo.fr",
        password:"nada01",
        isAdmin: false
    };
    var collaborator3 = {
        personnalIdNumber: "JHA0000",
        lastName: "Elkhadir",
        firstName: "Jihad",
        email:"elkhadir.jihad@gmail.com",
        password:"jihad01",
        isAdmin: true
    };
    collaborator1.password=hash(collaborator1.password);
    collaborator2.password=hash(collaborator2.password);
    collaborator3.password=hash(collaborator3.password);

    return {
        init: function () {
            return  $http.post("api/themes", topic1).then(function(data) {
                topic1 = data.data;
                if(data.data) {
                    console.log("ajout theme", topic1);
                    training.topicDescription = topic1;
                }
                return $http.post("api/themes", topic2)
            }).then(function (data) {
                topic2 = data.data;
                if(data.data) console.log("ajout theme", topic2);
              return $http.post("api/formations", training)
            }).then(function (data) {
                training = data.data;
                if (data.data){
                    console.log("ajout training", training);
                    session1.trainingDescription = training;
                    session2.trainingDescription = training;
                }
                return $http.post("api/collaborateurs", collaborator1);
            }).then(function (data) {
                collaborator1 = data.data;
                if (data.data)console.log("ajout collaborator1", collaborator1);
                return $http.post("api/collaborateurs", collaborator2);
            }).then(function (data) {
                collaborator2 = data.data;
                if (data.data)console.log("ajout collaborator2", collaborator2);
                return $http.post("api/collaborateurs", collaborator3);
            }).then(function (data) {
                collaborator3 = data.data;
                if (data.data)console.log("ajout collaborator3", collaborator3);
                return $http.post("api/sessions", session1);
            }).then(function (data) {
                session1 = data.data;
                if (data.data)console.log("ajout session1", session1);
                return $http.post("api/sessions", session2);
            }).then(function (data) {
                session2 = data.data;
                if (data.data)console.log("ajout session2", session2);
            });
        }
    };
}]);