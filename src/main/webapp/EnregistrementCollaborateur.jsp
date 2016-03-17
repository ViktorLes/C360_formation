<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%! 
	String accents = "áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ";
	String regexMatricule = "\"/^[A-Z0-9]+$/\""; 
	String regexNom = "\"/^[a-zA-Z-'."+accents+" ]+$/\""; 
	String regexPrenom = "\"/^[a-zA-Z-'"+accents+". ]+$/\""; 
%>

<html ng-app="myApp">
<head>
<meta charset="UTF-8">
<title>Enregistrement Collaborateur</title>
	<link href="css/bootstrap.css" rel="stylesheet" />
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
 
</head>


<body ng-controller="MyCtrl as main">

<div class="row">
 <div class="col-md-4">
 </div>
  <div class="col-md-4">
	<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">Enregistrement collaborateur</h3>
  </div>
  <div class="panel-body">
  <p>
  <form name="collaborateurForm" ng-submit="main.actionEnregistrer()" novalidate>

    <!-- MATRICULE -->
    <div class="form-group" ng-class="{ 'has-error' : collaborateurForm.matricule.$invalid && collaborateurForm.matricule.$dirty }">
      <input type="text" name="matricule" class="form-control" ng-model="main.collaborateur.matricule" required ng-pattern=<%=regexMatricule %>  placeholder="Matricule">
      <p ng-show="collaborateurForm.matricule.$invalid && collaborateurForm.matricule.$dirty" class="help-block">Veuillez entrer un numéro de matricule valide</p>
    </div>

    <!-- NOM -->
    <div class="form-group" ng-class="{ 'has-error' : collaborateurForm.nom.$invalid && collaborateurForm.nom.$dirty }">
      <input type="text" name="nom" class="form-control" ng-model="main.collaborateur.nom" required ng-pattern=<%=regexNom %> ng-minlength="2" placeholder="Nom">
      <p ng-show="collaborateurForm.nom.$invalid && collaborateurForm.nom.$dirty" class="help-block">Veuillez entrer un nom valide</p>
    </div>

    <!-- PRENOM -->
    <div class="form-group" ng-class="{ 'has-error' : collaborateurForm.prenom.$invalid && collaborateurForm.prenom.$dirty }">
      <input type="text" name="prenom" class="form-control" ng-model="main.collaborateur.prenom" required ng-pattern=<%=regexPrenom %> ng-minlength="2" placeholder="Prénom">
      <p ng-show="collaborateurForm.prenom.$invalid && collaborateurForm.prenom.$dirty" class="help-block">Veuillez entrer un prénom valide</p>
    </div>

    <div class="alert alert-danger" role="alert" ng-show="collaborateurForm.$invalid && collaborateurForm.$dirty">
      Veuillez remplir tous les champs
    </div>
	<span>{{main.collaborateur.matricule}}</span>
	<span>{{main.collaborateur.nom}}</span>
	<span>{{main.collaborateur.prenom}}</span>
    <button type="submit" class="btn btn-primary" ng-disabled="collaborateurForm.$invalid">Enregistrer</button>

  </form>
  </p>
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
			//console.log("Coucou nada",self.collaborateur);
			$http.post("api/collaborateurs", self.collaborateur).success(function(data){
		  		alert("done!!!!");
			});
	    };
	});
 	</script>

</body>
</html>