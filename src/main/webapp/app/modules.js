angular.module('filter', []);
angular.module('serviceDatepicker', ['ngAnimate', 'ui.bootstrap']);
angular.module('hash', []);
angular.module('controllers', ['serviceDatepicker','filter','hash']);
angular.module('App', ['ngRoute','controllers']);