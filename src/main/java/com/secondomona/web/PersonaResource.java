package com.secondomona.web;

import com.secondomona.persistence.model.Persona;
import com.secondomona.service.PersonaService;
import com.secondomona.web.model.PersonaRequest;
import com.secondomona.web.model.PersonaResponse;
import com.secondomona.web.model.VisitatoreRequest;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("api/persone")
@DenyAll
public class PersonaResource {

    private final PersonaService personaService;

    public PersonaResource(PersonaService personaService) {
        this.personaService = personaService;
    }
    @Path("/dipendenti")
    @POST
    @RolesAllowed("Admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PersonaResponse register(PersonaRequest personaRequest) {
        return personaService.createDipendenti(personaRequest);
    }
    @Path("/dipendenti")
    @GET
    @RolesAllowed({"Portineria", "Admin"})
    public List<Persona> getAllPersona() {
        return personaService.getAllDipendenti();
    }
    @Path("/visitatori")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Persona> getVisitatori() {
        return personaService.getVisitatori();
    }
    @Path("/visitatori")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Persona createVisitatore(VisitatoreRequest persona) {
        return personaService.creaVisitatore(persona);
    }
}
