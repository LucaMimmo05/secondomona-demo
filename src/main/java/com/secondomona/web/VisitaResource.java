package com.secondomona.web;

import com.secondomona.dto.RichiestaVisitaDTO;
import com.secondomona.persistence.model.RichiestaVisita;
import com.secondomona.service.VisitaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;


@Path("/api/visite")
@RolesAllowed({"refresh-token"})
public class VisitaResource {
    private VisitaService visitaService;

    public VisitaResource(VisitaService visitaService) {
        this.visitaService = visitaService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<RichiestaVisitaDTO> activeVisit() {
        return visitaService.getAllRichiesteVisite();
    }
}
