angular.module('GestForController').controller('globalController', ['InitBddService', function(InitBddService){
    var self = this;
    self.initBase = function() {
        InitBddService.init();
    }
}]);