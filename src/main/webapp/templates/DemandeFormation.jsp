<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.viseo.c360.formation.domain.formation.Formation" %>



<div class="row">
  <div class="col-md-4">
  </div>
  <div class="col-md-4">
	<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Demande Formation</h3>
		  </div>
		  <div class="panel-body">
			  <form name="formationForm" ng-submit="DmF.verifierForm(demandeForm)" novalidate >
				<div class="form-group">
				<label>Selectionner formation</label>
 						<select class="selectpicker form-control" ng-model="DmF.DemandeFormation" ng-options="formation.id as formation.titreformation for formation in DmF.formation" ng-change="DmF.loadSessionFormation()">
 						<option value="">--Selectionner une formation--</option>
 						</select>					
				</div>
				<button type="submit" class="btn btn-primary" >Envoyer demande</button>	
			  </form>
		</div>	 
	</div>
  </div>
</div>