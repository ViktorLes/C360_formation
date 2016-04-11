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
<<<<<<< HEAD

<div ng-controller="DatepickerDemoCtrl as ctrl">
<!-- d1 -->
=======
	
<!-- <div ng-controller="DatepickerDemoCtrl as ctrl">
d1
>>>>>>> 281d79ce63ab25fbea701f108405a034271c3045
    <div class="row">
      <div class="col-md-6">
        <p class="input-group">
          <input type="text" class="form-control" uib-datepicker-popup="dd-MMMM-yyyy" ng-model="ctrl.d1.dt" ng-click="ctrl.d1.open1()" is-open="ctrl.d1.popup1.opened" datepicker-options="ctrl.d1.dateOptions" ng-required="true" close-text="Close" alt-input-formats="ctrl.d1.altInputFormats" />
          <span class="input-group-btn">
            <button type="button" class="btn btn-default" ng-click="ctrl.d1.open1()"><i class="glyphicon glyphicon-calendar"></i></button>
          </span>
        </p>
      </div>
   
<<<<<<< HEAD
<!-- d2 -->   
=======
d2   
>>>>>>> 281d79ce63ab25fbea701f108405a034271c3045
      <div class="row">
      <div class="col-md-6">
        <p class="input-group">
          <input type="text" class="form-control" uib-datepicker-popup="dd-MMMM-yyyy" ng-model="ctrl.d2.dt" ng-click="ctrl.d2.open1()" is-open="ctrl.d2.popup1.opened" datepicker-options="ctrl.d2.dateOptions" ng-required="true" close-text="Close" alt-input-formats="ctrl.d2.altInputFormats" />
          <span class="input-group-btn">
            <button type="button" class="btn btn-default" ng-click="ctrl.d2.open1()"><i class="glyphicon glyphicon-calendar"></i></button>
          </span>
        </p>
      </div>
    </div>
<<<<<<< HEAD
  
=======
   -->
>>>>>>> 281d79ce63ab25fbea701f108405a034271c3045
  	<!-- JS -->
    <script src="lib/angular.js"></script>
    <script src="lib/angular-animate.js"></script> 
    <script src="lib/ui-bootstrap-tpls-1.2.5.js"></script>
	
<<<<<<< HEAD
	<script src="lib/angular-mocks.js"></script>
	<script src="lib/angular-route.js"></script>
=======
<!-- 	<script src="lib/angular-mocks.js"></script>
 -->	<script src="lib/angular-route.js"></script>
>>>>>>> 281d79ce63ab25fbea701f108405a034271c3045
	
	<script src="lib/angular-locale_fr-fr.js"></script>

    <script src="GestionFormation/Datepicker.js"></script>
	<script src="GestionFormation/Controllers.js"></script>
	<script src="GestionFormation/Routing.js"></script> 
  
  
</body>
</html>
