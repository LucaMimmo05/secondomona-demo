package com.secondomona.web;

import com.secondomona.service.PersonaService;
import com.secondomona.web.model.LoginRequest;
import com.secondomona.web.model.PersonaResponse;
import com.secondomona.web.model.TokenResponse;
import io.smallrye.jwt.build.Jwt;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.Claims;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

@Path("/auth")
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
        return Response.ok(new TokenResponse(accessToken, refreshToken)).build();
    }

    private String getAccessToken(PersonaResponse personaResponse) {
        return Jwt
                .issuer("secondomona-demo")
                .subject(String.valueOf(personaResponse.getId()))
                .upn(personaResponse.getEmail())
                .groups(Set.of(personaResponse.getRole(), "access-token"))
                .claim(Claims.email.name(), personaResponse.getEmail())
                .claim("name", personaResponse.getName())
                .claim("surname", personaResponse.getSurname())
                .expiresIn(Duration.ofMinutes(15))
                .issuedAt(Instant.now())
                .sign();
    }

    private String getRefreshToken(PersonaResponse personaResponse) {
        return Jwt
                .issuer("secondomona-demo")
                .subject(String.valueOf(personaResponse.getId()))
                .upn(personaResponse.getEmail())
                .groups(Set.of(personaResponse.getRole(), "refresh-token"))
                .claim(Claims.email.name(), personaResponse.getEmail())
                .claim("name", personaResponse.getName())
                .claim("surname", personaResponse.getSurname())
                .expiresIn(Duration.ofHours(1))
                .issuedAt(Instant.now())
                .sign();
    }

}
