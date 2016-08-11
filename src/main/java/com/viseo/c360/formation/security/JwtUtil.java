package com.viseo.c360.formation.security;

import com.viseo.c360.formation.dto.collaborator.CollaboratorToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;


public class JwtUtil {

    @Value("${key.jwt}")
    private String secret;

    public CollaboratorToken parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            CollaboratorToken collaborator = new CollaboratorToken();
            collaborator.setId(Long.parseLong((String) body.getSubject()));
            collaborator.setLastName((String) body.get("lastName"));
            collaborator.setFirstName((String) body.get("firstName"));
            collaborator.setRoles((String) body.get("roles"));
            return collaborator;
        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    public String generateToken(CollaboratorToken collaborator) {
        Claims claims = Jwts.claims().setSubject(Long.toString(collaborator.getId()));
        claims.put("firstName", collaborator.getFirstName());
        claims.put("lastName", collaborator.getLastName());
        claims.put("roles", collaborator.getRoles());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}