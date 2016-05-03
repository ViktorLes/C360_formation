package com.viseo.c360.formation.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.viseo.c360.formation.domain.collaborateur.Collaborateur;
import com.viseo.c360.formation.domain.formation.Formation;

@RestController
public class RegexWS {

	@RequestMapping(value="${endpoint.regexFormations}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> getRegexFormations(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("titreformation", "^"+Formation.Regex.TITRE_FORMATION+"+$");
		map.put("nombredemijournee","^"+Formation.Regex.NOMBRE_DEMI_JOURNEE +"+$");
		return map;
	}
	
	@RequestMapping(value="${endpoint.regexCollaborateurs}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> getRegexCollaborateurs(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("matricule", "^"+Collaborateur.Regex.matricule+"+$");
		map.put("nom","^"+Collaborateur.Regex.nom+"+$");
		map.put("prenom","^"+Collaborateur.Regex.prenom+"+$");
		return map;
	}
}
