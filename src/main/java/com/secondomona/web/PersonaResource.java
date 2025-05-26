package com.secondomona.web;

import com.secondomona.persistence.model.Persona;
import com.secondomona.service.PersonaService;
import com.secondomona.web.model.PersonaRequest;
import com.secondomona.web.model.PersonaResponse;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;

import java.util.List;

@Path("api/persone")
@DenyAll
public class PersonaResource {

    private final PersonaService personaService;

    public PersonaResource(PersonaService personaService) {
        this.personaService = personaService;
    }

    @POST
    @RolesAllowed({"Admin", "Portineria"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PersonaResponse register(@Context SecurityContext securityContext, PersonaRequest personaRequest) {
        String callerRole = securityContext.isUserInRole("Admin") ? "Admin" : "Portineria";
        if ("Portineria".equals(callerRole)) {
            if (!personaRequest.getVisitatore() || !"Visitatore".equalsIgnoreCase(personaRequest.getRuolo().toString())) {
                throw new ForbiddenException("I dipendenti possono registrare solo visitatori.");
            }
        }
        return personaService.createPersona(personaRequest);
    }

    @GET
    @RolesAllowed({"Portineria", "Admin"})
    public List<Persona> getAllPersona() {
        return personaService.getAllPersona();
    }
}
