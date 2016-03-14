package com.viseo.c360.formation.rest;

@RestController
public class CollaborateurController {
	
	@Inject
	MessageDAO collaborateurDAO;
	
	@RequestMapping(value = "${endpoint.collaborateurs.add}", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public String sayHelloWorld(){
		
		messageDAO.addCoucou();
        return "mon formulaire";
    }
	
	@RequestMapping(value = "${endpoint.collaborateurs.add}", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public String sayHelloWorld(){
		
		messageDAO.addCoucou();
        return "mon formulaire";
    }
}
