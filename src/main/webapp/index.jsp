<html>
<head>
	<meta charset="UTF-8">
	
    <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.css">
	<link href="css/formations.css" rel="stylesheet" />
	<link rel="stylesheet" href="node_modules/angular-bootstrap-datetimepicker/src/css/datetimepicker.css"/>
	
	<script type="text/javascript" src="node_modules/jquery/dist/jquery.js"></script>
	<script type="text/javascript" src="node_modules/bootstrap/dist/js/bootstrap.js"></script>
	<script type="text/javascript" src="node_modules/moment/moment.js"></script>
	<script type="text/javascript" src="node_modules/angular/angular.js"></script>
	
	<script src="lib/angular-mocks.js"></script>
	<script src="lib/angular-route.js"></script>
	
	<script type="text/javascript" src="node_modules/angular-bootstrap-datetimepicker/src/js/datetimepicker.js"></script>
	<script type="text/javascript" src="node_modules/angular-bootstrap-datetimepicker/src/js/datetimepicker.templates.js"></script>
	
	<script src="GestionFormation/Controllers.js"></script>
	<script src="GestionFormation/Routing.js"></script> 
	
</head>

<body ng-app="routeApp">
		<header class="bg-primary row">
			    <div class="col-md-1"></div> 
				<div id="titreAppliquette" class="col-md-9">Gestions des formations</div>
				<div id="titreProjet" class="col-md-2">Collaborateur 360</div>
		</header>
		<a href="#/EnregistrementCollaborateur">EC</a>
		<a href="#/DeclarationFormation">DF</a>	
		<div ng-view></div>

	<div class="dropdown">
	  <a class="dropdown-toggle" id="dropdown2" role="button" data-toggle="dropdown" data-target="#" href="#">
	    <div class="input-group">
	    	<input type="text" class="form-control" data-ng-model="dateDropDownInput"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
	    </div>
	  </a>
	  {{dateDropDownInput}}
	  <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
	    <datetimepicker data-ng-model="dateDropDownInput" data-datetimepicker-config="{ dropdownSelector: '#dropdown2', startView:'day', minView:'day' }"/>
	  </ul>
	</div>

</body>
</html>
