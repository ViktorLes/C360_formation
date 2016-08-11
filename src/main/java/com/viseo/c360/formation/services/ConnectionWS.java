package com.viseo.c360.formation.services;

import com.viseo.c360.formation.converters.collaborator.CollaboratorToToken;
import com.viseo.c360.formation.dao.CollaboratorDAO;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.dto.collaborator.CollaboratorCredentials;
import com.viseo.c360.formation.dto.collaborator.CollaboratorDescription;
import com.viseo.c360.formation.dto.collaborator.CollaboratorToken;
import com.viseo.c360.formation.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
public class ConnectionWS {

    private class Token {
        String token;

        public Token() {
        }

        public Token(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    @Inject
    JwtUtil jwtUtil;
    @Inject
    CollaboratorDAO collaboratorDAO;
    @Inject
    AuthenticationManager authenticationManager;

    @RequestMapping(value = "${endpoint.login}", method = RequestMethod.POST)
    @ResponseBody
    public Token login(@RequestBody CollaboratorCredentials collaboratorCredentials) {
        Collaborator collaborator = collaboratorDAO.getCollaboratorByLoginPassword(collaboratorCredentials.getEmail(), collaboratorCredentials.getPassword());
        CollaboratorToken collaboratorToken = new CollaboratorToToken().convert(collaborator);
        //authenticationManager.
        return new Token(jwtUtil.generateToken(collaboratorToken));
    }


    @RequestMapping(value = "${endpoint.logout}", method = RequestMethod.POST)
    @ResponseBody
    public void logout(@RequestBody Token token) {

    }
}
