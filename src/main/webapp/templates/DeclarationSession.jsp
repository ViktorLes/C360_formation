	<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>

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
				    <div class="form-group">
				   	<h5> <span class="label label-default">Nom de la formation: </span></h5>
				   	   <select class="selectpicker form-control" name="titreformation">
				   	   	<option> Javascript </option>
				   	   	<option> Hibernate </option>
				   	   	 <option ng-repeat="titre in DS.formation track by $index">{{DS.formation[0].titreformation}}</option>
				   	   </select>
	
     				<!-- <select class="selectpicker form-control" name="titreformation"  ng-options="formation.titreformation for formation in formations">
				   		<option value="">Sectionner une formation</option>
					 -->
					</div>
				    
				   
			 <!-- Date -->
				<!-- d1 -->
				     <div class="form-group">
				     <h5> <span class="label label-default">Date (JJ/MM/AAAA): </span></h5>
				      <div class="col-md-6">
				        <p class="input-group">
				          <input type="text" name="date" class="form-control" uib-datepicker-popup="dd/MM/yyyy" ng-model="DS.d1.dt" required ng-pattern=<%=regexDate%> ng-trim="true" ng-click="DS.d1.open1()" is-open="DS.d1.popup1.opened" datepicker-options="DS.d1.dateOptions" ng-required="true" close-text="Close" alt-input-formats="DS.d1.altInputFormats" />
				          <span class="input-group-btn">
				            <button type="button" class="btn btn-default" ng-click="DS.d1.open1()"><i class="glyphicon glyphicon-calendar"></i></button>
				         	      <p ng-show="sessionForm.date.$invalid && sessionForm.date.$dirty" class="help-block">Date Invalide: Veuillez saisir une date sous le format (JJ/MM/AAAA)</p>
				          </span>
				        </p>
				      </div>
				   
				<!-- d2 -->   
				     <div class="form-group">
				      <div class="col-md-6">
				        <p class="input-group">
				          <input type="text" name="date" class="form-control" uib-datepicker-popup="dd/MM/yyyy" ng-model="DS.d2.dt" ng-click="DS.d2.open1()" is-open="DS.d2.popup1.opened" datepicker-options="DS.d2.dateOptions" ng-required="true" close-text="Close" alt-input-formats="DS.d2.altInputFormats" />				          
				          <span class="input-group-btn">
				            <button type="button" class="btn btn-default" ng-click="DS.d2.open1()"><i class="glyphicon glyphicon-calendar"></i></button>
				         	      <p ng-show="sessionForm.date.$invalid && sessionForm.date.$dirty" class="help-block">Date Invalide: Veuillez saisir une date sous le format (JJ/MM/AAAA)</p>
				          </span>
				        </p>
				      </div>
				    </div>
				   
					      <!-- Heure de la session -->
				     <div class="form-group">
				        <h5> <span class="label label-default">Heure du Debut: (1er Jour)</span></h5>
				     <select class="selectpicker form-control">
				   	<option ng-repeat="horaire in DS.monTab track by $index"> {{horaire}}</option> 
				     </select>
				   	</div>
				   	
 				    <div class="form-group" ng-class="{ 'has-error' : sessionForm.titreformation.$invalid && sessionForm.titreformation.$dirty }">
				        <h5> <span class="label label-default">Heure de fin: (Dernier Jour)</span></h5>
     					 <select class="selectpicker form-control">
 				   	<option ng-repeat="horaire in DS.monTab track by $index"> {{horaire}}</option> 
				     </select>
				    </div>
		 
				    <!-- Lieu de la session -->
				    <div class="form-group" ng-class="{ 'has-error' : sessionForm.titreformation.$invalid && sessionForm.titreformation.$dirty }">
				        <h5> <span class="label label-default">Lieu</span></h5>
     					 <select class="selectpicker form-control">
				     	<option>Salle Phuket</option>
				     	<option>Salle Bali</option>
     				     </select>
				    </div>
				    
				    <div class="alert alert-danger" role="alert" ng-show="sessionForm.$invalid && sessionForm.$dirty">
			     	 Veuillez remplir tous les champs
			    	</div> 
			    	
				    <button type="submit" class="btn btn-primary" ng-enabled="sessionForm.$valid">Enregistrer</button>
				  </form>
				  </div>
			</div>
		</div>
	</div>