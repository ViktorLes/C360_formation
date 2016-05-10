angular.module('App')
    .config(['$routeProvider',function($routeProvider) {
                     $routeProvider
                     .when('/EnregistrementCollaborateur', {
                         templateUrl: 'templates/registerCollaborator.html',
                         controller: 'controllerRegisterCollaborator',
                         controllerAs:'EC'
                     })
                     .when('/DeclarationFormation', {
                         templateUrl: 'templates/registerTraining.html',
                         controller: 'controllerRegisterTraining',
                         controllerAs:'DF'
                     })
                     .when('/DeclarationSession', {
                         templateUrl: 'templates/registerTrainingSession.html',
                         controller: 'controllerRegisterTrainingSession',
                         controllerAs:'DS'
                     })
                     .when('/AffectationSession', {
                         templateUrl: 'templates/affectTrainingSession.html',
                         controller: 'controllerAffectTraining',
                         controllerAs:'AS'
                     })
                     .when('/DemandeFormation', {
                         templateUrl: 'templates/requestTraining.html',
                         controller: 'controllerRequestTraining',
                         controllerAs:'DmF'
                     });  
                 }
             ]);