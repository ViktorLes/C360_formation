<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.viseo.c360.formation.domain.formation.Formation" %>

<%! 
	String regexnombredemijournee = "\"/^"+Formation.regexNombreDemiJournee+"+$/\""; 
	String regexTitredelaformation = "\"/^"+Formation.regexTitreFormation+"+$/\""; 
%>

<div class="row">
  
  <div class="col-md-4">
  </div>
  
  <div class="col-md-4">
	<div class="panel panel-default">
	
		  <div class="panel-heading">
		    <h3 class="panel-title">Déclaration de la formation</h3>
		  </div>
		  <div class="panel-body">
			  <form name="formationForm" ng-submit="DF.actionEnregistrer()" novalidate>
			
			    <!-- Titre de la Formation -->
			    <div class="form-group" ng-class="{ 'has-error' : formationForm.titreformation.$invalid && formationForm.titreformation.$dirty }">
			      <input type="text" name="titreformation" class="form-control" ng-model="DF.formation.titreformation" required ng-pattern=<%=regexTitredelaformation %> ng-trim="true"  placeholder="Titre de la formation"> <!-- ng-minlength="2" ng-maxlength="20" -->
			      <p ng-show="formationForm.titreformation.$invalid && formationForm.titreformation.$dirty" class="help-block">Veuillez entrer un titre de formation valide</p>
			    </div>
			
			        <!-- Nombre de 1/2 journée -->
			    <div class="form-group" ng-class="{ 'has-error' : formationForm.nombredemijournee.$invalid && formationForm.nombredemijournee.$dirty }">
			      <input type="number" name="nombredemijournee" class="form-control no-spinner" ng-model="DF.formation.nombredemijournee" required ng-pattern=<%=regexnombredemijournee %> min="1" max="200"  placeholder="Nombre de 1/2 journée">
			      <p ng-show="formationForm.nombredemijournee.$invalid && formationForm.nombredemijournee.$dirty" class="help-block">Veuillez entrer un nombre de formation valide (Entre 1 et 200)</p>
			    </div>	
			
			    <div class="alert alert-danger" role="alert" ng-show="formationForm.$invalid && formationForm.$dirty">
			      Veuillez remplir tous les champs
			    </div>
			    <!-- {{DF.formation.nombredemijournee}}
			    {{DF.formation.titreformation}} -->
			    <button type="submit" class="btn btn-primary" ng-disabled="formationForm.$invalid">Enregistrer</button>		
			  </form>
		  </div>
		  
		 <div class="col-md-4">
		 </div>
		 
	</div>
  </div>
</div>