	<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
	
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	 
	 <%@ page import="com.viseo.c360.formation.domain.formation.SessionFormation" %>
	<%! 
		
		 String regexFormationSession = "\"/^"+SessionFormation.regextitreFormation+"+$/\""; 
		String regexDate = "\"/^"+SessionFormation.regexDate+"+$/\""; 
	%> 
	<!-- CSS -->
		
	<div class="row"> 
	  <div class="col-md-4">
	  </div>
	 
	 <div class="col-md-4">
		<div class="panel panel-default">
		
			  <div class="panel-heading">
			    <h3 class="panel-title">Déclaration d'une session</h3>
			  </div>
			  
			  <div class="panel-body">
				  <form name="sessionForm" ng-submit="DS.actionEnregistrer()" novalidate>
				
				 <!-- Nom de la Formation -->
				   <div class="form-group" ng-class="{ 'has-error' : sessionForm.titreformation.$invalid && sessionForm.titreformation.$dirty }">
				   	<label>Nom de la formation: </label>
				    <select name="Formation"></select>
				    </div>
				    
				      <!-- Date de la session -->
				    <div class="form-group" ng-class="{ 'has-error' : sessionForm.titreformation.$invalid && sessionForm.titreformation.$dirty }">
				     <label>Date de la formation: </label>
					<input type="date" size="3" />  <label>au</label>		<input type="date" size="3" />			
				     <span class="error" ng-show="sessionForm.input.$error.required">Date Valide!</span>
				     <span class="error" ng-show="sessionForm.input.$error.date">Date Invalide!</span>
					</div>
					
					      <!-- Heure de la session -->
				    <div class="form-group" ng-class="{ 'has-error' : sessionForm.titreformation.$invalid && sessionForm.titreformation.$dirty }">
					 <label>Heure du Debut: (1er Jour)</label>
				     <select name="HeureDebut">
				   	<option ng-repeat="horaire in DS.monTab track by $index"> {{horaire}}</option> 
				     </select>
				   	</div>
				    
				    <div class="form-group" ng-class="{ 'has-error' : sessionForm.titreformation.$invalid && sessionForm.titreformation.$dirty }">
					 <label>Heure de Fin: (Dernier Jour)</label>
				     <select name="HeureFin" >
				   	<option ng-repeat="horaire in DS.monTab track by $index"> {{horaire}}</option> 
				   	
				     </select>
				    </div>
				    
				    <!-- Lieu de la session -->
				    <div class="form-group" ng-class="{ 'has-error' : sessionForm.titreformation.$invalid && sessionForm.titreformation.$dirty }">
					 <label>Lieu: </label>
				     <select class= "selectpicker" name="Lieu" >
				     	<option>Salle Phuket</option>
				     	<option>Salle Bali</option>
				 	 <p ng-show="sessionForm.lieu.$invalid && sessionForm.lieu.$dirty" class="help-block">Veuillez entrer un nom valide</p>
				     </select>
				    </div>
				    
				    <div class="alert alert-danger" role="alert" ng-show="sessionForm.$invalid && sessionForm.$dirty">
			     	 Veuillez remplir tous les champs
			    	</div>
			    	
				    <button type="submit" class="btn btn-primary" ng-disabled="sessionForm.$invalid">Enregistrer</button>
				  </form>
				  </div>
			</div>
		</div>
	</div>