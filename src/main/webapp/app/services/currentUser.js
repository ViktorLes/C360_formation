angular.module('authentication', ['angular-jwt'])
    .service('currentUserService', ['$http', 'jwtHelper', function ($http, jwtHelper) {
        var self = this;
        return {
            decodeThisToken: function (userToken) {
                var tokenPayload = jwtHelper.decodeToken(userToken.data['userConnected']);
                this.setUserData(userToken.data['userConnected'], tokenPayload['sub'], tokenPayload['roles']);
            },
            disconnectCurrentUser: function () {
                var self = this;
                $http.post("api/userdisconnect", this.getUserToken()).then(function () {
                        self.setUserData('', '', '')
                    }).catch(function () {
                        console.log("Error !! User is not connected");
                })
            },
            setUserData: function (userToken, newUserName, newUserRole) {
                self.token = userToken;
                self.name = newUserName;
                self.roles = newUserRole;
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