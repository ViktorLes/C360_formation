package com.viseo.c360.formation.security;

import com.viseo.c360.formation.dto.collaborator.CollaboratorToken;
import com.viseo.c360.formation.exceptions.security.JwtTokenMalformedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.inject.Inject;
import java.util.List;

public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Inject
    JwtUtil jwtUtil;

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(authentication);//(JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getToken();

        CollaboratorToken parsedCollaborator = jwtUtil.parseToken(token);

        if (parsedCollaborator == null) {
            throw new JwtTokenMalformedException("JWT token is not valid");
        }

        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(parsedCollaborator.getRoles());

        return new AuthenticatedUser(parsedCollaborator.getId(), parsedCollaborator.getUsername(), token, authorityList);
    }

}
