function MyCtrl($scope) {
    $scope.name = "Matricule";
    $scope.action = function() {
        alert($scope.name)
    }
}
