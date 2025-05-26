package com.secondomona.web;

import com.secondomona.persistence.PersonaRepository;
import com.secondomona.persistence.model.Persona;
import com.secondomona.service.PersonaService;
import com.secondomona.web.model.PersonaRequest;
import com.secondomona.web.model.PersonaResponse;
import com.secondomona.web.model.VisitatoreRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/visitatori")
public class VisitatoreResource {


    private final PersonaService personaService;


    public VisitatoreResource(PersonaService personaRepository) {
        this.personaService = personaRepository;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Persona> getVisitatori() {
        return personaService.getVisitatori();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Persona createVisitatore(VisitatoreRequest persona) {
        return personaService.creaVisitatore(persona);
    }

}
