<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
	
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<body>
		<div class="row">
			<div class="col-md-2">
			</div>
		  
		  	<div class="col-md-8">
				<div class="panel panel-default">
					  
				  		<div class="panel-body">		
						    <form>
								<h3 class="panel-title spacer">Affectation d'une session</h3>
								  <form name="affsessionForm" ng-submit="AS.actionEnregistrer()" novalidate>
							    <select required name=affectation width=300 STYLE="width: 350px" placeholder="Selectionner une session ...">
									    <option value="1">truc1</option> 
									    <option value="2">truc2</option> 
									    <option value="3">truc3</option> 
								</select>
							</form>
					    </div>
					    
					    <form >
						    
								<div class="col-md-5 well">
											<h3 class="panel-title">Chercher Collaborateurs</h3>
											<select name="ddl" width=300 STYLE="width:350px" size=6>
												<option value=''>truc</option>
												<option value=''>truc</option>
												<option value=''>truc</option>
												<option value=''>truc</option>
												<option value=''>truc</option>
												<option value=''>truc</option>
												<option value=''>truc</option>
												<option value=''>truc</option>
												<option value=''>truc</option>
												<option value=''>truc</option>
											</select>
								</div>
						

							<div class="col-md-1">	
								<button class="glyphicon glyphicon-chevron-right"></button>
							</div>

							
							 	<div class="col-md-5">
									<h3 class="panel-title">Collaborateurs ajoutes: X collaborateurs</h3> 
									<select name="ddl" width=300 STYLE="width:350px" size=6>
										<option value=''>truc</option>
										<option value=''>truc</option>
										<option value=''>truc</option>
										<option value=''>truc</option>
										<option value=''>truc</option>
										<option value=''>truc</option>
										<option value=''>truc</option>
										<option value=''>truc</option>
										<option value=''>truc</option>
										<option value=''>truc</option>
									</select>
								</div>	
						</form>

					<div class="row">
							 <button type="submit" class="btn btn-primary" ng-disabled="affsessionForm.$invalid">Enregistrer</button>	
					</div>	
				</div>
		
		</div>
	</body>