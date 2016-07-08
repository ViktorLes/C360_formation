angular.module('filter', []);
angular.module('serviceDatepicker', ['ngAnimate', 'ui.bootstrap']);
angular.module('hashService', []);
angular.module('controllers', ['serviceDatepicker','filter','hashService']);
angular.module('App', ['ngRoute','controllers']);