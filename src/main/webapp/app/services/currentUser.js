angular.module('authentication', ['angular-jwt'])
    .service('currentUserService', ['$http', 'jwtHelper', function ($http, jwtHelper) {
        var self = this;
        return {
            decodeThisToken: function (userToken) {
                var tokenPayload = jwtHelper.decodeToken(userToken.data['userConnected']);
                this.setUserData(userToken.data['userConnected'], tokenPayload['sub'], tokenPayload['lastName'], tokenPayload['roles'], tokenPayload['id']);
            },
            disconnectCurrentUser: function () {
                var self = this;
                $http.post("api/userdisconnect", this.getUserToken()).then(function () {
                    self.setUserData('', '', '', '')
                }).catch(function () {
                    console.log("Error !! User is not connected");
                })
            },
            setUserData: function (userToken, newFirstName, newLastName, newUserRole, newUserId) {
                self.token = userToken;
                self.firstName = newFirstName;
                self.lastName = newLastName;
                self.roles = newUserRole;
                self.userId = newUserId;
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
            },
            getCollaboratorIdentity: function () {
                return {id: self.userId};
            }
        };
    }]);