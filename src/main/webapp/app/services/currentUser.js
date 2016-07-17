angular.module('authentication', [])
    .service('currentUserService', ['$http', function ($http) {
        var self = this;
        return {
            getUserNameFromServer: function (userToken) {
                return $http.post("api/userName", userToken).then(function (userNameResponse) {
                    var response = JSON.parse(JSON.stringify(userNameResponse));
                    return response.data['userName'];
                })
            },
            setUserData: function (newUserName, userToken) {
                self.name = newUserName;
                self.token = userToken;
            },
            getUserName: function () {
                return self.name;
            },
            getUserToken: function () {
                return self.token;
            }
        };
    }]);