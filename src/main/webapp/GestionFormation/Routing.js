'use strict';
console.log("Routing ok!")
var routeApp = angular.module('routeApp', [
    // Dépendances du "module"
    'ngRoute',
    'GestForController']);

routeApp.config(['$routeProvider',function($routeProvider) {  
                     // Système de routage
                     $routeProvider
                     .when('/EnregistrementCollaborateur', {
                         templateUrl: 'templates/EnregistrementCollaborateur.jsp',
                         controller: 'CtrlCol'
                     })
                     .when('/DeclarationFormation', {
                         templateUrl: 'templates/DeclarationFormation.jsp',
                         controller: 'CtrlFor'
                     });
                     /*
                     .otherwise({
                         redirectTo: '/'
                        
                     });
                      */
                 }
             ]);
	console.log("Routing marche!")