angular.module('authentication', [])
  .service('currentUserService', ['$http',function($http) {
    var user = window.user;
    return {
        getUserNameFromServer: function(userToken) {
            $http.post("api/userName", userToken).success(function (userNameResponse) {
                var response = JSON.parse(JSON.stringify(userNameResponse));
                return response['userName'];
            });
        },
        setUserToken: function(newUser,userToken) {
            name = newUser;
            token =userToken;
        },
        getUserName: function (user) {
            return user.name;
        },
        getUserToken:function (user) {
            return user.token;
        },
        isConnected: function() {
            return !!user;
        }
    };
}]);