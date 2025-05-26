package com.secondomona.web;

import com.secondomona.persistence.PersonaRepository;
import com.secondomona.persistence.model.Persona;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/visitatori")
public class VisitatoreResource {


    private final PersonaRepository personaRepository;


    public VisitatoreResource(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }


    @GET

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Persona> getVisitatori() {
        return personaRepository.getVisitatori();
    }

}
