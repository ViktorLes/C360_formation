<html>
<head>
	<meta charset="UTF-8">
	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<!--  	<script src="GestionFormation/Controllers.js"></script>
	<script src="GestionFormation/Routing.js"></script> -->
	<link href="css/bootstrap.min.css" rel="stylesheet" />
	
</head>
<body ng-app="Apptest">
	<div ng-controller="cntrlTest">
		<input type="text" ng-model="ModelTest"/>
		<!-- Liaison input et span -->
		<span> {{ModelTest}} </span>
	</div>
	<script type="text/javascript" ></script>
	<!-- <div><a href="HelloWorld.html">Hello world !</a></div> -->
	<div><a href="templates/EnregistrementCollaborateur.jsp">Enregistrement d'un collaborateur</a></div>
	<div><a href="templates/DeclarationFormation.jsp">Déclaration d'une formation</a></div>
	<!-- <div><a href="testAddCollaborateur.html">testAddCollaborateur.html</a></div> -->
</body>
</html>
