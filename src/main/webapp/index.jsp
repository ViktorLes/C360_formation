<html>
<head>
	<meta charset="UTF-8">
	
    <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.css"> 
    <!-- <link href="css/bootstrap.css" rel="stylesheet" /> -->
    <link href="css/datepicker/datepicker.css" rel="stylesheet" />
    <link href="css/datepicker/popup.css" rel="stylesheet" />
	<link href="css/formations.css" rel="stylesheet" />
<!-- 	<link rel="stylesheet" href="node_modules/angular-bootstrap-datetimepicker/src/css/datetimepicker.css"/>
	 -->
<!-- 	<script type="text/javascript" src="node_modules/jquery/dist/jquery.js"></script>
	<script type="text/javascript" src="node_modules/bootstrap/dist/js/bootstrap.js"></script>
	<script type="text/javascript" src="node_modules/moment/moment.js"></script>
	<script type="text/javascript" src="node_modules/angular/angular.js"></script>
	 -->
	 <script src="lib/angular.js"></script>
	<script src="lib/angular-mocks.js"></script>
	<script src="lib/angular-route.js"></script>
	<script src="lib/angular-locale_fr-fr.js"></script>
	<script src="lib/ui-bootstrap-tpls-1.2.5.js"></script>
	<script src="lib/datepicker/dateparser.js"></script>
	<script src="lib/datepicker/popup.js"></script>
	<script src="lib/datepicker/datepicker.js"></script>
	
	
	<!-- <script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.6.0.js"></script>
	 <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet"> -->
	
<!-- 	<script type="text/javascript" src="node_modules/angular-bootstrap-datetimepicker/src/js/datetimepicker.js"></script>
	<script type="text/javascript" src="node_modules/angular-bootstrap-datetimepicker/src/js/datetimepicker.templates.js"></script>
	 -->
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

<!-- 	<div class="dropdown">
	  <a class="dropdown-toggle" id="dropdown2" role="button" data-toggle="dropdown" data-target="#" href="#">
	    <div class="input-group">
	    	<input type="text" class="form-control" data-ng-model="dateDropDownInput"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
	    </div>
	  </a>
	  {{dateDropDownInput}}
	  <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
	    <datetimepicker data-ng-model="dateDropDownInput" data-datetimepicker-config="{ dropdownSelector: '#dropdown2', startView:'day', minView:'day' }"/>
	  </ul>
	</div> -->
	
	<div ng-controller="DatepickerDemoCtrl">
    <pre>Selected date is: <em>{{dt | date:'fullDate' }}</em></pre>

    <h4>Inline</h4>
    <div style="display:inline-block; min-height:290px;">
      <uib-datepicker ng-model="dt" class="well well-sm" datepicker-options="inlineOptions"></uib-datepicker>
    </div>

    <h4>Popup</h4>
    <div class="row">
      <div class="col-md-6">
        <p class="input-group">
          <input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="dt" is-open="popup1.opened" datepicker-options="dateOptions" ng-required="true" close-text="Close" alt-input-formats="altInputFormats" />
          <span class="input-group-btn">
            <button type="button" class="btn btn-default" ng-click="open1()"><i class="glyphicon glyphicon-calendar"></i></button>
          </span>
        </p>
      </div>

      <div class="col-md-6">
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
</div>

</body>
</html>
