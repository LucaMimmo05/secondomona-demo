package com.secondomona.web;

import com.secondomona.persistence.model.Persona;
import com.secondomona.service.PersonaService;
import com.secondomona.web.model.PersonaRequest;
import com.secondomona.web.model.PersonaResponse;
import com.secondomona.web.model.VisitatoreRequest;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.List;

@Path("/api")
@DenyAll
public class PersonaResource {

    private final PersonaService personaService;

    public PersonaResource(PersonaService personaService) {
        this.personaService = personaService;
    }

    @Path("/dipendenti")
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
        return personaService.createDipendenti(personaRequest);
    }

    @Path("/dipendenti")
    @GET
    @RolesAllowed({"Admin", "Portineria"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Persona> getAllPersona() {
        return personaService.getAllDipendenti();
    }

    @Path("/dipendenti/{id}")
    @GET
    @RolesAllowed({"Admin", "Portineria", "access-token"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDipendenteById(@PathParam("id") Long id) {
        try {
            // Chiamiamo il servizio per recuperare il dipendente tramite ID
            PersonaResponse persona = personaService.getPersonaById(id);

            // Se la persona non è stata trovata, restituiamo 404 Not Found
            if (persona == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Dipendente non trovato con ID: " + id)
                        .build();
            }

            // Altrimenti restituiamo i dati della persona
            return Response.ok(persona).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Errore durante il recupero del dipendente: " + e.getMessage())
                    .build();
        }
    }

    @Path("/visitatori")
    @RolesAllowed({"access-token"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Persona> getVisitatori() {
        return personaService.getVisitatori();
    }

    @Path("/visitatori")
    @RolesAllowed({"Portineria", "Admin"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Persona createVisitatore(VisitatoreRequest persona) {
        return personaService.createVisitatore(persona);
    }
}
