package com.viseo.c360.formation.services;

import java.util.HashMap;
import java.util.Map;

import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.dto.collaborator.CollaboratorDTO;
import com.viseo.c360.formation.dto.training.TrainingDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegexWS {

	@RequestMapping(value="${endpoint.regexTrainings}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> getRegexTrainings(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("TRAINING_TITLE", "^"+ TrainingDTO.Regex.TRAINING_TITLE +"+$");
		map.put("NUMBER_HALF_DAYS","^"+ TrainingDTO.Regex.NUMBER_HALF_DAYS +"+$");
		return map;
	}
	
	@RequestMapping(value="${endpoint.regexCollaborators}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> getRegexCollaborators(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("PERSONNAL_ID_NUMBER", "^"+ CollaboratorDTO.Regex.PERSONNAL_ID_NUMBER +"+$");
		map.put("LAST_NAME","^"+ CollaboratorDTO.Regex.LAST_NAME +"+$");
		map.put("FIRST_NAME","^"+ CollaboratorDTO.Regex.FIRST_NAME +"+$");
		return map;
	}
}
