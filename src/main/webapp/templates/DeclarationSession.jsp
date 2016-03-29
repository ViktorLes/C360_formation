	<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
	
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<%-- 
	<%@ page import="com.viseo.c360.session.domain.session.Session" %>
	<%! 
		
		String regexNomsession = "\"/^"+Session.regexNomsession+"+$/\""; 
		String regexDate = "\"/^"+Session.regexDate+"+$/\""; 
	%> --%>
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
				   <div>
				   	<label>Nom de la formation: </label>
				    <select name="Formation"></select>
				    </div>
				    
				     <!-- Date de la session -->
					 <div>
				     <label>Date de la formation: </label>
					<!-- <input type="date" data-uk-datepicker="{format:'DD.MM.YYYY'}" id="exampleInput" name="datedebut" placeholder="jj/mm/aaaa" min="2016-01-01" max="2016-12-31" required />   -->
					   <div>
		      		 <div>
					    <input type="text" ng-model="date" class="datepicker"></input>
					</div>
					<label>au</label>
					 <input type="date" id="exampleInput" name="datefin" placeholder="jj/mm/aaaa" min="2016-01-01" max="2016-12-31" required />
				   	<calendar selected="day"></calendar>
				    <!--  <span class="error" ng-show="sessionForm.input.$error.required">Date Valide!</span>
				     <span class="error" ng-show="sessionForm.input.$error.date">Date Invalide!</span> -->
					</div>
	
				      <!-- Heure de la session -->
				    <div>
					 <label>Heure du Debut: (1er Jour)</label>
				     <select name="HeureDebut">
				     <option>08:00</option>
				     <option>08:30</option>
				     </select>
				    </div>
				    <div>
					 <label>Heure de Fin: (Dernier Jour)</label>
				     <select name="HeureFin" >
				   	<option ng-repeat="horaire in DS.monTab track by $index"> {{horaire}}</option> 
				     </select>
				    </div>
				    <!-- Lieu de la session -->
				    <div>
					 <label>Lieu: </label>
				     <select name="Lieu">
				     	<option>Salle Phuket</option>
				     	<option>Salle Bali</option>
				     </select>
				    </div>
				    <button type="submit" class="btn btn-primary" ng-disabled="sessionForm.$invalid">Enregistrer</button>
				  </form>
				  </div>
			</div>
		</div>
	</div>