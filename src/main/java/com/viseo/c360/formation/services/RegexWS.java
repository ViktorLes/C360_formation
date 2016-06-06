package com.viseo.c360.formation.services;

import java.util.HashMap;
import java.util.Map;

<<<<<<< HEAD
import com.viseo.c360.formation.dto.collaborator.CollaboratorDescription;
import com.viseo.c360.formation.dto.training.TrainingDescription;
=======
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.dto.collaborator.CollaboratorDTO;
import com.viseo.c360.formation.dto.training.TrainingDTO;
>>>>>>> 5d51c637147120bde988d153a12ca2c9d2a1d1f9
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
<<<<<<< HEAD
		map.put("TRAINING_TITLE", "^"+ TrainingDescription.Regex.TRAINING_TITLE +"+$");
		map.put("NUMBER_HALF_DAYS","^"+ TrainingDescription.Regex.NUMBER_HALF_DAYS +"+$");
=======
		map.put("TRAINING_TITLE", "^"+ TrainingDTO.Regex.TRAINING_TITLE +"+$");
		map.put("NUMBER_HALF_DAYS","^"+ TrainingDTO.Regex.NUMBER_HALF_DAYS +"+$");
>>>>>>> 5d51c637147120bde988d153a12ca2c9d2a1d1f9
		return map;
	}
	
	@RequestMapping(value="${endpoint.regexCollaborators}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> getRegexCollaborators(){
		Map<String,String> map = new HashMap<String,String>();
<<<<<<< HEAD
		map.put("PERSONNAL_ID_NUMBER", "^"+ CollaboratorDescription.Regex.PERSONNAL_ID_NUMBER +"+$");
		map.put("LAST_NAME","^"+ CollaboratorDescription.Regex.LAST_NAME +"+$");
		map.put("FIRST_NAME","^"+ CollaboratorDescription.Regex.FIRST_NAME +"+$");
=======
		map.put("PERSONNAL_ID_NUMBER", "^"+ CollaboratorDTO.Regex.PERSONNAL_ID_NUMBER +"+$");
		map.put("LAST_NAME","^"+ CollaboratorDTO.Regex.LAST_NAME +"+$");
		map.put("FIRST_NAME","^"+ CollaboratorDTO.Regex.FIRST_NAME +"+$");
>>>>>>> 5d51c637147120bde988d153a12ca2c9d2a1d1f9
		return map;
	}
}
