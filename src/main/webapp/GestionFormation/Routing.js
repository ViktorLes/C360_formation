var routeApp = angular.module('App', [
    // Dépendances du "module"
    'ngRoute',
    'GestForController']);

routeApp.config(['$routeProvider',function($routeProvider) {  
                     // Système de routage
                     $routeProvider
                     .when('/EnregistrementCollaborateur', {
                         templateUrl: 'templates/EnregistrementCollaborateur.jsp',
                         controller: 'CtrlCol as EC',
                         controllerAs:'EC'
                     })
                     .when('/DeclarationFormation', {
                         templateUrl: 'templates/DeclarationFormation.jsp',
                         controller: 'CtrlFor',
                         controllerAs:'DF'
                     })
                     .when('/DeclarationSession', {
                         templateUrl: 'templates/DeclarationSession.jsp',
                         controller: 'CtrlSes',
                         controllerAs:'DS'
                     })
                     .when('/AffectationSession', {
                         templateUrl: 'templates/AffectationSession.jsp',
                         controller: 'CtrlAffectationSession',
                         controllerAs:'AS'
                     });
                     /*
                     .otherwise({
                         redirectTo: '/'
                        
                     });
                      */
                 }
             ]);