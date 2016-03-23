var routeApp = angular.module('routeApp', [
    // Dépendances du "module"
    'ngRoute',
    'GestForController']);

routeApp.config(['$routeProvider',function($routeProvider) {  
                     // Système de routage
                     $routeProvider
                     .when('/EnregistrementCollaborateur', {
                         templateUrl: 'templates/EnregistrementCollaborateur.jsp',
                         controller: 'CtrlCol',
                         controllerAs:'EC'
                     })
                     .when('/DeclarationFormation', {
                         templateUrl: 'templates/DeclarationFormation.jsp',
                         controller: 'CtrlFor',
                         controllerAs:'DF'

                     });
                     /*
                     .otherwise({
                         redirectTo: '/'
                        
                     });
                      */
                 }
             ]);
