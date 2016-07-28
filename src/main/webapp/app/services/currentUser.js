angular.module('authentication', ['angular-jwt'])
    .service('currentUserService', ['$http', 'jwtHelper', function ($http, jwtHelper) {
        var self = this;
        return {
            decodeThisToken: function (userToken) {
                var tokenPayload = jwtHelper.decodeToken(userToken.data['userConnected']);
                console.log("userToken: ", userToken);
                console.log("tokenPayload['sub']: ", tokenPayload['sub']);
                console.log("tokenPayload['roles']: ", tokenPayload['roles']);
                return this.setUserData(userToken.data['userConnected'], tokenPayload['sub'], tokenPayload['roles']);
            },
            disconnectCurrentUser: function () {
                console.log("token: ", this.getUserToken())
                $http.post("api/user", this.getUserToken()).then(this.setUserData('', '', ''));
            },
            setUserData: function (userToken, newUserName, newUserRole) {
                self.token = userToken;
                self.name = newUserName;
                self.roles = newUserRole;
                return this;
            },
            getUserToken: function () {
                return self.token;
            },
            getUserName: function () {
                return self.name;
            },
            getUserRole: function () {
                return self.roles;
            }
        };
    }]);