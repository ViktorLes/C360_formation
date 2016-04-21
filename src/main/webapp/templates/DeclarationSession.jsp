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
				  <form name="sessionForm" ng-submit="DS.actionEnregistrer()" novalidate class="from-inline">
				
				 <!-- Nom de la Formation -->
				    <div class="form-group">
				   	<h5> <span class="label label-default">Nom de la formation: </span></h5>
				   	   <select class="selectpicker form-control" name="sessionFormation" ng-model="DS.SessionFormationId" ng-options='formation.id as formation.titreformation for formation in DS.formation' ></select>
					</div>
					
			 <!-- Date -->
				<!-- d1 -->
				     <div class="form-group">
				     <h5> <span class="label label-default">Date de debut: </span></h5>
				      <div class="col-md-6">
				        <p class="input-group">

				          <input type="text" name="date1"  ng-pattern= '^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((1[6-9]|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((1[6-9]|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((1[6-9]|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$'
				                 class="form-control" uib-datepicker-popup="dd/MM/yyyy"  ui-date="{ dateFormat: 'dd/MM/yyyy' }" ng-model="DS.d1.dt" ng-trim="true" ng-click="DS.d1.open1()" is-open="DS.d1.popup1.opened" datepicker-options="DS.d1.dateOptions" ng-required="true" close-text="Close" alt-input-formats="DS.d1.altInputFormats" />
				          <span class="input-group-btn">
				            <button type="button" class="btn btn-default" ng-click="DS.d1.open1()"><i class="glyphicon glyphicon-calendar"></i></button>
				          </span>
				           <div class="alert alert-warning" ng-show="sessionForm.date1.$error.required">
 							 <strong>Attention : </strong> Veuillez saisir une date
							</div>
							<div class="alert alert-danger" ng-show="sessionForm.date1.$error.pattern && sessionForm.date1.$dirty || sessionForm.date1.$invalid">
							  <strong></strong> Date invalide! Saisissez une date sous le format : (JJ/MM/AAAA)
							</div>
				         </p>
				      </div>	  
				  
				<!-- HEURE DEBUT !!!!!!!!!!!!!!!! --> 
				 <div class="form-group">
				 <h5> <span class="label label-default">Heure du Debut: (1er Jour)</span></h5>
				  <div class="col-md-6">
				     <select class="selectpicker form-control" ng-model="DS.heureDebut" ng-options='horaire as horaire for horaire in DS.monTab'></select>
				   	</div>
				    </div>
				    
				    
				   	  <!-- d2 -->
				     <div class="form-group">
				      <h5> <span class="label label-default">Date de fin: </span></h5>
				      <div class="col-md-6">
				        <p class="input-group">

				          <input type="text" name="date2"  ng-pattern= '^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((1[6-9]|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((1[6-9]|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((1[6-9]|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$' 
				          		 class="form-control" uib-datepicker-popup="dd/MM/yyyy" ng-model="DS.d2.dt" ng-click="DS.d2.open1()" is-open="DS.d2.popup1.opened" datepicker-options="DS.d2.dateOptions" ng-required="true" close-text="Close" alt-input-formats="DS.d2.altInputFormats" />				          
				          <span class="input-group-btn">
				            <button type="button" class="btn btn-default" ng-click="DS.d2.open1()"><i class="glyphicon glyphicon-calendar"></i></button>
				           </span>
				           <div class="alert alert-warning" ng-show="sessionForm.date2.$error.required">
 							 <strong>Attention :</strong> Veuillez saisir une date
							</div>
							<div class="alert alert-danger" ng-show="sessionForm.date2.$error.pattern && sessionForm.date2.$dirty || sessionForm.date2.$invalid">
							  <strong></strong> Date invalide! Saisissez une date sous le format : (JJ/MM/AAAA)
							</div>
				        </p>
				      </div>
				   
					      <!-- HEURE FIN !!!!!!!!!!!!!! -->
				    
 				    <div class="form-group">
				        <h5> <span class="label label-default">Heure de fin: (Dernier Jour)</span></h5>
				   		  <div class="col-md-6">
     					 <select class="selectpicker form-control" ng-model="DS.heureFin" ng-options='horaire as horaire for horaire in DS.monTab'></select>
				    </div>
		 
				    <!-- Lieu de la session -->
				    <div class="form-group">
				        <h5> <span class="label label-default">Lieu</span></h5>
     					 <select class="selectpicker form-control" ng-model="DS.lieuFormation">
				     		<option>Salle Phuket</option>
				     		<option>Salle Bali</option>
     				     </select>
				    </div>
				  
			    	 <div class="alert alert-danger" role="alert" ng-show="!DS.isSessionAlreadyPlanned">
					     Il existe déjà une session pour cette formation dans ces tranches horaires.
					 </div>  
					 <div class="alert alert-danger" role="alert" ng-show="!DS.DateCorrect(d1,d2)">
					     Veuillez choisir une date de fin superieure à la date de debut.
					 </div> 
			    	
				    <button type="submit" class="btn btn-primary" ng-disabled="sessionForm.$invalid">Enregistrer</button>				  </form>
				  </div>
			</div>
		</div>
	</div>