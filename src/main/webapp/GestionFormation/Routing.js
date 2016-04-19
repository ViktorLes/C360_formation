var routeApp = angular.module('App', [
    // Dépendances du "module"
    'ngRoute',
    'GestForController']);

routeApp.config(['$routeProvider',function($routeProvider) {  
                     // Système de routage
                     $routeProvider
                     .when('/EnregistrementCollaborateur', {
<<<<<<< HEAD
                         templateUrl: 'templates/EnregistrementCollaborateur.html',
                         controller: 'CtrlCol as EC',
                        // controllerAs:'EC'
=======
                          templateUrl: 'templates/EnregistrementCollaborateur.jsp',
                         controller: 'CtrlCol',
                         controllerAs:'EC'
>>>>>>> b4169f785023fbd5e0ee4a48795428f916d53cb9
                     })
                     .when('/DeclarationFormation', {
                         templateUrl: 'templates/DeclarationFormation.html',
                         controller: 'CtrlFor',
                         controllerAs:'DF'
                     })
                     .when('/DeclarationSession', {
                         templateUrl: 'templates/DeclarationSession.jsp',
                         controller: 'CtrlSes',
                         controllerAs:'DS'
                     });
                     /*
                     .otherwise({
                         redirectTo: '/'
                        
                     });
                      */
                 }
             ]);