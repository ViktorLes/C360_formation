<html>
<head>
	<meta charset="UTF-8">
	
	<!-- JS -->
	<script src="lib/angular.js"></script>
<!--<script src="lib/angular-mocks.js"></script>-->	
	<script src="lib/angular-route.js"></script>
	
	<script src="GestionFormation/Controllers.js"></script>
	<script src="GestionFormation/Routing.js"></script> 
	
	<!-- CSS -->
	<link href="css/bootstrap.min.css" rel="stylesheet" />
	<link href="css/formations.css" rel="stylesheet" />
	<link href="css/bootstrap-datePicker.css" rel="stylesheet" />
	
</head>

<body ng-app="routeApp">
	<a href="#/EnregistrementCollaborateur">EC</a>
	<a href="#/DeclarationFormation">DF</a>	
	<a href="#/DeclarationSession">DS</a>	
	<div ng-view></div>
</body>
</html>
