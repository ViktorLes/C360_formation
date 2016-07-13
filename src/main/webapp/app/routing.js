angular.module('App')
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/pageblanche',{
                templateUrl: 'pageblanche.html'
            })
            .when('/Authentication', {
            templateUrl: 'templates/authentication.html'
        });
        }
    ]);