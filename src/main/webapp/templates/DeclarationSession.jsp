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
	<link href="css/bootstrap.min.css" rel="stylesheet" />
	<link href="css/formations.css" rel="stylesheet" />
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
				 <input type="date" id="exampleInput" name="input" placeholder="jj/mm/aaaa" min="2016-01-01" max="2016-12-31" required />
				 <label>au</label>
				 <input type="date" id="exampleInput" name="input" placeholder="jj/mm/aaaa" min="2016-01-01" max="2016-12-31" required />
				<!-- <span class="error" ng-show="sessionForm.input.$error.required">Date Valide!</span>
			     	  <span class="error" ng-show="sessionForm.input.$error.date">Date Invalide!</span> -->
				</div>

			      <!-- Heure de la session -->
			    <div>
				 <label>Heure du Debut: (1er Jour)</label>
			    
			    </div>
			      <!-- Lieu de la session -->
			    <div></div>
			    <button type="submit" class="btn btn-primary" ng-disabled="sessionForm.$invalid">Enregistrer</button>
			  </form>
			  </div>
		</div>
	</div>
</div>