package com.secondomona.web;

import com.secondomona.dto.RichiestaVisitaDTO;
import com.secondomona.persistence.model.RichiestaVisita;
import com.secondomona.service.VisitaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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

    /**
     * Endpoint per concludere una visita
     *
     * @param idRichiesta ID della richiesta di visita da concludere
     * @return La richiesta di visita aggiornata o 404 se non trovata
     */
    @Path("/{idRichiesta}/conclusione")
    @PUT
    public Response concludiVisita(@PathParam("idRichiesta") Integer idRichiesta) {
        RichiestaVisitaDTO visitaAggiornata = visitaService.concludiVisita(idRichiesta);
        if (visitaAggiornata == null) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity("Visita con ID " + idRichiesta + " non trovata")
                .build();
        }
        return Response.ok(visitaAggiornata).build();
    }

    @POST
    @RolesAllowed({"access-token"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RichiestaVisitaDTO createVisit(RichiestaVisitaDTO visitaDTO) {
        RichiestaVisita savedEntity = visitaService.createRichiestaVisitaFromDTO(visitaDTO);
        return visitaService.toDTO(savedEntity);
    }

    @POST
    @Path("/{id}/termina")
    @RolesAllowed({"Admin", "Portineria"})
    public void endVisit(@PathParam("id") Integer id) {
        visitaService.concludiVisita(id);
    }
}
