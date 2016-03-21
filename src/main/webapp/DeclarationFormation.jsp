<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%! 
	String accents = "áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ";
	String regexnombredemijournee = "\"/^[0-9]+$/\""; 
	String regexTitredelaformation = "\"/^[a-zA-Z-'-+%.:@#"+accents+" ]+$/\""; 
	
%>

<html ng-app="myApp">
<head>
<meta charset="UTF-8">
<title>Déclaration de la formation</title>
	<link href="css/bootstrap.min.css" rel="stylesheet" />
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
 
</head>


<body ng-controller="MyCtrl as main">

<div class="row">
  
  <div class="col-md-4">
  </div>
  
  <div class="col-md-4">
	<div class="panel panel-default">
	
		  <div class="panel-heading">
		    <h3 class="panel-title">Déclaration de la formation</h3>
		  </div>
		  
		  <div class="panel-body">
			  <form name="formationForm" ng-submit="main.actionEnregistrer()" novalidate>
			
			    <!-- Titre de la Formation -->
			    <div class="form-group" ng-class="{ 'has-error' : formationForm.titreformation.$invalid && formationForm.titreformation.$dirty }">
			      <input type="text" name="titreformation" class="form-control" ng-model="main.formation.titreformation" required ng-pattern=<%=regexTitredelaformation %> ng-minlength="2" ng-maxlength="20" ng-trim="true"  placeholder="Titre de la formation">
			      <p ng-show="formationForm.titreformation.$invalid && formationForm.titreformation.$dirty" class="help-block">Veuillez entrer un titre de formation valide</p>
			    </div>
			
			        <!-- Nombre de 1/2 journée -->
			    <div class="form-group" ng-class="{ 'has-error' : formationForm.nombredemijournee.$invalid && formationForm.nombredemijournee.$dirty }">
			      <input type="number" name="nombredemijournee" class="form-control" ng-model="main.formation.nombredemijournee" required ng-pattern=<%=regexnombredemijournee %> ng-minlength="1" ng-maxlength="2"  placeholder="Nombre de 1/2 journée">
			      <p ng-show="formationForm.nombredemijournee.$invalid && formationForm.nombredemijournee.$dirty" class="help-block">Veuillez entrer un nombre de formation valide</p>
			    </div>
			
			    <div class="alert alert-danger" role="alert" ng-show="formationForm.$invalid && formationForm.$dirty">
			      Veuillez remplir tous les champs
			    </div>
			    <!-- {{main.formation.nombredemijournee}}
			    {{main.formation.titreformation}} -->
			    <button type="submit" class="btn btn-primary" ng-disabled="formationForm.$invalid">Enregistrer</button>
			
			  </form>
		  </div>
		  
		 <div class="col-md-4">
		 </div>
		 
	</div>
  </div>
</div>

	<script type="text/javascript">
	
		var app = angular.module('myApp', []);
		app.controller('MyCtrl', function($http) {
			var self = this;
			self.actionEnregistrer = function() {
				self.formation.titreformation= self.formation.titreformation.replace(/ +/g, " ");
				//self.formation.nombredemijournee= self.formation.nombredemijournee.replace(/ +/g, " ");
				$http.post("api/formations", self.formation).success(function(data){
			 		document.location.href = 'pageblancheformation.html';
					
				});
		    };
		});
	
 	</script>
</body>
</html>