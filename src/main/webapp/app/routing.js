angular.module('App')
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/pageblanche',{
                templateUrl: 'pageblanche.html'
            })
            .when('/manageSession', {
                templateUrl: 'templates/manageSession.html'
            });
        }
    ]);