angular.module('App')
    .config(['$routeProvider',function($routeProvider) {
                     $routeProvider
                     .when('/EnregistrementCollaborateur', {
                         templateUrl: 'templates/registerCollaborator.html',
                         controller: 'CtrlCol as EC',
                         controllerAs:'EC'
                     })
                     .when('/DeclarationFormation', {
                         templateUrl: 'templates/registerTraining.html',
                         controller: 'CtrlFor',
                         controllerAs:'DF'
                     })
                     .when('/DeclarationSession', {
                         templateUrl: 'templates/registerTrainingSession.html',
                         controller: 'CtrlSes',
                         controllerAs:'DS'
                     })
                     .when('/AffectationSession', {
                         templateUrl: 'templates/affectTrainingSession.html',
                         controller: 'CtrlAffectationSession',
                         controllerAs:'AS'
                     })
                     .when('/DemandeFormation', {
                         templateUrl: 'templates/requestTraining.html',
                         controller: 'CtrlDemandeForm',
                         controllerAs:'DmF'
                     });  
                 }
             ]);