package com.secondomona.web;

import com.secondomona.service.PersonaService;
import com.secondomona.web.model.AccessTokenResponse;
import com.secondomona.web.model.LoginRequest;
import com.secondomona.web.model.PersonaResponse;
import com.secondomona.web.model.TokenResponse;
import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.Claims;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

@Path("/api/auth")
public class AuthenticationResource {

    private final PersonaService personaService;

    public AuthenticationResource(PersonaService personaService) {
        this.personaService = personaService;
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        PersonaResponse personaResponse = personaService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (personaResponse == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        String accessToken = getAccessToken(personaResponse);
        String refreshToken = getRefreshToken(personaResponse);

        // Creiamo la risposta includendo l'ID della tessera e l'ID della persona
        TokenResponse tokenResponse = new TokenResponse(accessToken, refreshToken, personaResponse.getIdTessera(), personaResponse.getId());

        return Response.ok(tokenResponse).build();
    }

    @POST
    @Path("/refresh")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"refresh_token"})
    public Response refresh(@Context SecurityContext securityContext) {
        String email = securityContext.getUserPrincipal().getName();
        PersonaResponse personaResponse = personaService.getPersonaByEmail(email);
        if (personaResponse == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .build();
        }
        String accessToken = getAccessToken(personaResponse);
        return Response.ok(new AccessTokenResponse(accessToken)).build();
    }

    private String getAccessToken(PersonaResponse personaResponse) {
        var jwtBuilder = Jwt
                .issuer("secondomona-demo")
                .subject(String.valueOf(personaResponse.getId()))
                .upn(personaResponse.getEmail())
                .groups(Set.of(personaResponse.getRole(), "access-token"))
                .claim(Claims.email.name(), personaResponse.getEmail())
                .claim("name", personaResponse.getName())
                .claim("surname", personaResponse.getSurname())
                .claim("idPersona", personaResponse.getId());

        // Aggiungi idTessera solo se non è null
        if (personaResponse.getIdTessera() != null) {
            jwtBuilder.claim("idTessera", personaResponse.getIdTessera());
        }

        return jwtBuilder
                .expiresIn(Duration.ofMinutes(15))
                .issuedAt(Instant.now())
                .sign();
    }

    private String getRefreshToken(PersonaResponse personaResponse) {
        var jwtBuilder = Jwt
                .issuer("secondomona-demo")
                .subject(String.valueOf(personaResponse.getId()))
                .upn(personaResponse.getEmail())
                .groups(Set.of(personaResponse.getRole(), "refresh-token"))
                .claim(Claims.email.name(), personaResponse.getEmail())
                .claim("name", personaResponse.getName())
                .claim("surname", personaResponse.getSurname())
                .claim("idPersona", personaResponse.getId());

        // Aggiungi idTessera solo se non è null
        if (personaResponse.getIdTessera() != null) {
            jwtBuilder.claim("idTessera", personaResponse.getIdTessera());
        }

        return jwtBuilder
                .expiresIn(Duration.ofHours(1))
                .issuedAt(Instant.now())
                .sign();
    }

}
