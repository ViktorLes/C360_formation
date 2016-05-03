angular.module('AppFilter', []);
angular.module('Datepicker', ['ngAnimate', 'ui.bootstrap']);
angular.module('GestForController', ['Datepicker','AppFilter']);
angular.module('App', ['ngRoute','GestForController']);