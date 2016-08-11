angular.module('controllers').factory('TestConnectionService', ['$http','hash', function ($http,hash) {
    var collaborator1 = {
        personnalIdNumber: "TLE3467",
        lastName: "Lecomte",
        firstName: "Thomas",
        email:"Thomas.lecomte@yahoo.fr",
        password:"thomas",
        roles: "USER"
    };
    var collaborator2 = {
        personnalIdNumber: "NKA7896",
        lastName: "Kalmouni",
        firstName: "Nada",
        email:"Nada.kalmouni@yahoo.fr",
        password:"nada",
        roles: "USER"
    };
    var collaborator3 = {
        personnalIdNumber: "JHA0000",
        lastName: "Elkhadir",
        firstName: "Jihad",
        email:"elkhadir.jihad@gmail.com",
        password:"jihad",
        isAdmin: "ADMIN"
    };

    collaborator1.password=hash(collaborator1.password);
    collaborator2.password=hash(collaborator2.password);
    collaborator3.password=hash(collaborator3.password);

    return {
        init: function () {
            return  $http.post("api/signup", collaborator1).then(function (data) {
                collaborator1 = data.data;
                if (data.data)console.log("ajout collaborator1", collaborator1);
                return $http.post("api/signup", collaborator2);
            }).then(function (data) {
                collaborator2 = data.data;
                if (data.data)console.log("ajout collaborator2", collaborator2);
                return $http.post("api/signup", collaborator3);
            }).then(function (data) {
                collaborator3 = data.data;
                if (data.data)console.log("ajout collaborator3", collaborator3);
                return $http.post("api/login", {email: collaborator1.email, password: collaborator1.password});
            }).then(function (data) {
                console.log("token returned", data.data);
            });
        }
    };
}]);