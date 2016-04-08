<html>
<head>
	<meta charset="UTF-8">
	
<<<<<<< HEAD
	<!-- JS -->
	<script src="lib/angular.js"></script>
<!--<script src="lib/angular-mocks.js"></script>-->	
=======
    <link href="css/bootstrap.css" rel="stylesheet" /> 
	<link href="css/formations.css" rel="stylesheet" />

    <script src="lib/angular.js"></script>
    <script src="lib/angular-animate.js"></script> 
    <script src="lib/ui-bootstrap-tpls-1.2.5.js"></script>
	
	<script src="lib/angular-mocks.js"></script>
>>>>>>> datePickerFrench
	<script src="lib/angular-route.js"></script>
	
	<script src="lib/angular-locale_fr-fr.js"></script>

    <script src="GestionFormation/Datepicker.js"></script>
	<script src="GestionFormation/Controllers.js"></script>
	<script src="GestionFormation/Routing.js"></script> 
<<<<<<< HEAD
	
	<!-- CSS -->
	<link href="css/bootstrap.min.css" rel="stylesheet" />
	<link href="css/formations.css" rel="stylesheet" />
	
=======
>>>>>>> datePickerFrench
</head>

<body ng-app="routeApp">
		<header class="bg-primary row">
			    <div class="col-md-1"></div> 
				<div id="titreAppliquette" class="col-md-9">Gestions des formations</div>
				<div id="titreProjet" class="col-md-2">Collaborateur 360</div>
		</header>
<<<<<<< HEAD
	<a href="#/EnregistrementCollaborateur">EC</a>
	<a href="#/DeclarationFormation">DF</a>	
	<a href="#/DeclarationSession">DS</a>	
	<div ng-view></div>
=======
		<a href="#/EnregistrementCollaborateur">EC</a>
		<a href="#/DeclarationFormation">DF</a>	
		<div ng-view></div>

	<div ng-controller="DatepickerDemoCtrl as ctrl">
   <!--  <pre>Selected date is: <em>{{dt | date:'fullDate' }}</em></pre>

    <h4>Inline</h4>
    <div style="display:inline-block; min-height:290px;">
      <uib-datepicker ng-model="dt" class="well well-sm" datepicker-options="inlineOptions"></uib-datepicker>
    </div> -->

    <h4>Popup</h4>
    <div class="row">
      <div class="col-md-6">
        <p class="input-group">
          <input type="text" class="form-control" uib-datepicker-popup="dd-MMMM-yyyy" ng-model="ctrl.dt" ng-click="ctrl.datepicker.open1()" is-open="ctrl.datepicker.popup1.opened" datepicker-options="ctrl.datepicker.dateOptions" ng-required="true" close-text="Close" alt-input-formats="ctrl.datepicker.altInputFormats" />
          <span class="input-group-btn">
            <button type="button" class="btn btn-default" ng-click="ctrl.datepicker.open1()"><i class="glyphicon glyphicon-calendar"></i></button>
          </span>
        </p>
      </div>
  <!--
      <div class="row">
      <div class="col-md-6">
        <p class="input-group">
          <input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="dt" ng-click="open1()" is-open="popup1.opened" datepicker-options="dateOptions" ng-required="true" close-text="Close" alt-input-formats="altInputFormats" />
          <span class="input-group-btn">
            <button type="button" class="btn btn-default" ng-click="open1()"><i class="glyphicon glyphicon-calendar"></i></button>
          </span>
        </p>
      </div>
-->
      <!-- <div class="col-md-6">
        <p class="input-group">
          <input type="text" class="form-control" uib-datepicker-popup ng-model="dt" is-open="popup2.opened" datepicker-options="dateOptions" ng-required="true" close-text="Close" />
          <span class="input-group-btn">
            <button type="button" class="btn btn-default" ng-click="open2()"><i class="glyphicon glyphicon-calendar"></i></button>
          </span>
        </p>
      </div>
    </div>
    <div class="row">
      <div class="col-md-6">
        <label>Format: <span class="muted-text">(manual alternate <em>{{altInputFormats[0]}}</em>)</span></label> <select class="form-control" ng-model="format" ng-options="f for f in formats"><option></option></select>
      </div>
    </div> 

    <hr />
    <button type="button" class="btn btn-sm btn-info" ng-click="today()">Today</button>
    <button type="button" class="btn btn-sm btn-default" ng-click="setDate(2009, 7, 24)">2009-08-24</button>
    <button type="button" class="btn btn-sm btn-danger" ng-click="clear()">Clear</button>
    <button type="button" class="btn btn-sm btn-default" ng-click="toggleMin()" uib-tooltip="After today restriction">Min date</button>
    -->
</div>

>>>>>>> datePickerFrench
</body>
</html>
