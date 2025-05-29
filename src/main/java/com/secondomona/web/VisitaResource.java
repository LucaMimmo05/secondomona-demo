package com.secondomona.web;

import com.secondomona.dto.RichiestaVisitaDTO;
import com.secondomona.persistence.model.RichiestaVisita;
import com.secondomona.service.VisitaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/visite")
@RolesAllowed({"refresh-token", "access-token"})
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VisitaResource {

    private final VisitaService visitaService;

    public VisitaResource(VisitaService visitaService) {
        this.visitaService = visitaService;
    }

    @GET
    public List<RichiestaVisitaDTO> listaVisite() {
        return visitaService.getAllRichiesteVisite();
    }

    @Path("/attive")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<RichiestaVisitaDTO> visiteAttive() {
        return visitaService.getVisiteAttive();
    }

    @Path("/in-attesa")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<RichiestaVisitaDTO> visiteInAttesa() {
        return visitaService.getVisiteInAttesa();
    }

    @POST
    @RolesAllowed({"Admin", "Dipendente"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RichiestaVisitaDTO createVisit(RichiestaVisitaDTO visitaDTO) {
        RichiestaVisita savedEntity = visitaService.createRichiestaVisitaFromDTO(visitaDTO);
        return visitaService.toDTO(savedEntity);
    }
}
