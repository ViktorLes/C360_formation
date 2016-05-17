angular.module('filter', []);
angular.module('serviceDatepicker', ['ngAnimate', 'ui.bootstrap']);
angular.module('controllers', ['serviceDatepicker','filter']);
angular.module('App', ['ngRoute','controllers']);