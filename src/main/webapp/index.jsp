<html>
<head>
	<meta charset="UTF-8">
	<script src="lib/angular.js"></script>
	<script src="lib/angular-mocks.js"></script>
	<script src="lib/angular-route.js"></script>
	
	<script src="GestionFormation/Controllers.js"></script>
	<script src="GestionFormation/Routing.js"></script> 
	<link href="css/bootstrap.min.css" rel="stylesheet" />
	
</head>
<body ng-app="routeApp">

	<div ng-controller="cntrlTest">
		<!-- <input type="text" ng-model="ModelTest"/>
		Liaison input et span
		<span> {{ModelTest}} </span> -->
	</div>
	<a href="#/EnregistrementCollaborateur">EC</a>
	<a href="#/DeclarationFormation">DF</a>
	
	<div ng-view></div>
</body>
</html>
