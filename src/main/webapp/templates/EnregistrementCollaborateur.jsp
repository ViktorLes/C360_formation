<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.viseo.c360.formation.domain.collaborateur.Collaborateur" %>
<%! 
	//Déclaration des regex 
	String regexMatricule = "\"/^"+Collaborateur.regexMatricule+"+$/\""; 
	String regexNom = "\"/^"+Collaborateur.regexNom+"+$/\""; 
	String regexPrenom = "\"/^"+Collaborateur.regexPrenom+"+$/\""; 
%>

<div class="row">

  <div class="col-md-4">
  </div>
  
  <div class="col-md-4">
	<div class="panel panel-default">
	
		  <div class="panel-heading">
		    <h3 class="panel-title">Enregistrement collaborateur</h3>
		  </div>
		  
		  <div class="panel-body">
			  <form name="collaborateurForm" ng-submit="EC.verifierForm(collaborateurForm)" novalidate>
			
			   		    <!-- MATRICULE -->
			    <div class="form-group" ng-class="{ 'has-error' : collaborateurForm.matricule.$invalid && collaborateurForm.matricule.$dirty }">

			      <input type="text" name="matricule" class="form-control" ng-model="EC.collaborateur.matricule" required ng-pattern=<%=regexMatricule%> ng-minlength="3" ng-maxlength="20"  placeholder="Matricule">
			      <p ng-show="collaborateurForm.matricule.$invalid && collaborateurForm.matricule.$dirty" class="help-block">Numéro de matricule invalide (Exemple valide : ABC1234)</p>

			    </div>
			
			    <!-- NOM -->
			    <div class="form-group" ng-class="{ 'has-error' : collaborateurForm.nom.$invalid && collaborateurForm.nom.$dirty }">
			      <input type="text" name="nom" class="form-control" ng-model="EC.collaborateur.nom" required ng-pattern=<%=regexNom%> ng-minlength="2" ng-maxlength="120" ng-trim="true"  placeholder="Nom">
			      <p ng-show="collaborateurForm.nom.$invalid && collaborateurForm.nom.$dirty" class="help-block">Veuillez entrer un nom valide</p>
			    </div>
			
			    <!-- PRENOM -->
			    <div class="form-group" ng-class="{ 'has-error' : collaborateurForm.prenom.$invalid && collaborateurForm.prenom.$dirty }">
			      <input type="text" name="prenom" class="form-control" ng-model="EC.collaborateur.prenom" required ng-pattern=<%=regexPrenom%> ng-minlength="2" ng-maxlength="120" ng-trim="true" placeholder="Prénom">
			      <p ng-show="collaborateurForm.prenom.$invalid && collaborateurForm.prenom.$dirty" class="help-block">Veuillez entrer un prénom valide</p>
			    </div>
			
			    <div class="alert alert-danger" role="alert" ng-show="EC.isFalseForm">
			      Veuillez remplir tous les champs
			    </div>
			    
			    <div class="alert alert-danger" role="alert" ng-show="!EC.isNewMatricule">
			      Ce matricule a déjà été enregistré
			    </div> 
			    
			    <button type="submit" class="btn btn-primary" >Enregistrer</button>			
			  </form>
		  </div>
		  
		 <div class="col-md-4">
		 </div>	 
	</div>
  </div>
</div>
