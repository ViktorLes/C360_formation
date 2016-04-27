<html>
<head>
	<meta charset="UTF-8">

	<!-- CSS -->
	<link href="css/bootstrap.css" rel="stylesheet" /> 
	<link href="css/formations.css" rel="stylesheet" />
	
</head>

	<body ng-app="App">
		<header class="bg-primary row">
			    <div class="col-md-1"></div> 
				<div id="titreAppliquette" class="col-md-9">Gestions des formations</div>
				<div id="titreProjet" class="col-md-2">Collaborateur 360</div>
		</header>

	<a href="#/EnregistrementCollaborateur">EC</a>
	<a href="#/DeclarationFormation">DF</a>	
	<a href="#/DeclarationSession">DS</a>	
	<div ng-view></div>

 
  	<!-- JS -->
    <script src="lib/angular.js"></script>
    <script src="lib/angular-animate.js"></script> 
    <script src="lib/ui-bootstrap-tpls-1.2.5.js"></script>
	
<!-- 	<script src="lib/angular-mocks.js"></script>
 -->	<script src="lib/angular-route.js"></script>
	
	<script src="lib/angular-locale_fr-fr.js"></script>

    <script src="GestionFormation/Datepicker.js"></script>
	<script src="GestionFormation/Controllers.js"></script>
	<script src="GestionFormation/Routing.js"></script> 
  
  
</body>
</html>
