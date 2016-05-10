angular.module('App')
    .config(['$routeProvider',function($routeProvider) {
                     $routeProvider
                     .when('/RegisterCollaborator', {
                         templateUrl: 'templates/registerCollaborator.html',
                         controller: 'controllerRegisterCollaborator',
                         controllerAs:'EC'
                     })
                     .when('/RegisterTraining', {
                         templateUrl: 'templates/registerTraining.html',
                         controller: 'controllerRegisterTraining',
                         controllerAs:'DF'
                     })
                     .when('/RegisterTrainingSession', {
                         templateUrl: 'templates/registerTrainingSession.html',
                         controller: 'controllerRegisterTrainingSession',
                         controllerAs:'DS'
                     })
                     .when('/AffectTraining', {
                         templateUrl: 'templates/affectTrainingSession.html',
                         controller: 'controllerAffectTraining',
                         controllerAs:'AS'
                     })
                     .when('/RequestTraining', {
                         templateUrl: 'templates/requestTraining.html',
                         controller: 'controllerRequestTraining',
                         controllerAs:'DmF'
                     });  
                 }
             ]);