angular.module('authentication', ['angular-jwt'])
    .service('currentUserService', ['$http', 'jwtHelper', function ($http, jwtHelper) {
        var self = this;
        return {
            decodeThisToken: function (userToken) {
                var tokenPayload = jwtHelper.decodeToken(userToken.data['userConnected']);
                this.setUserData(userToken.data['userConnected'], tokenPayload['sub'],tokenPayload['lastName'], tokenPayload['roles']);
            },
            disconnectCurrentUser: function () {
                var self = this;
                $http.post("api/userdisconnect", this.getUserToken()).then(function () {
                        self.setUserData('', '','', '')
                    }).catch(function () {
                        console.log("Error !! User is not connected");
                })
            },
            setUserData: function (userToken, newFirstName,newLastName, newUserRole) {
                self.token = userToken;
                self.firstName = newFirstName;
                self.lastName = newLastName;
                self.roles = newUserRole;
            },
            getUserToken: function () {
                return self.token;
            },
            getUserFirstName: function () {
                return self.firstName;
            },
            getUserLastName: function () {
                return self.lastName;
            },
            getUserRole: function () {
                return self.roles;
            }
        };
    }]);