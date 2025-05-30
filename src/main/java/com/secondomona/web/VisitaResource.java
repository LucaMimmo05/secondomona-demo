package com.secondomona.web;

import com.secondomona.dto.RichiestaVisitaDTO;
import com.secondomona.persistence.model.RichiestaVisita;
import com.secondomona.service.AssegnazioneBadgeService;
import com.secondomona.service.VisitaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.OffsetDateTime;
import java.util.List;

@Path("/api/visite")
@RolesAllowed({"refresh-token", "access-token"})
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VisitaResource {
    private final VisitaService visitaService;
    private final AssegnazioneBadgeService assegnazioneBadgeService;

    public VisitaResource(VisitaService visitaService, AssegnazioneBadgeService assegnazioneBadgeService) {
        this.visitaService = visitaService;
        this.assegnazioneBadgeService = assegnazioneBadgeService;
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
    public Response creaVisitaEAssegnaBadge(RichiestaVisitaDTO visitaDTO) {
        RichiestaVisita visita = visitaService.createRichiestaVisitaFromDTO(visitaDTO);
        // Rimossa l'assegnazione automatica del badge per evitare errori quando non ci sono tessere disponibili
        return Response.status(Response.Status.CREATED)
                .entity(visitaService.toDTO(visita))
                .build();
    }

    @PUT
    @Path("/{idRichiesta}/concludi-visita")
    @RolesAllowed({"Admin", "Portineria"})
    public Response concludiVisitaETerminaBadge(@PathParam("idRichiesta") Integer idRichiesta) {
        RichiestaVisitaDTO visitaDTO = visitaService.concludiVisita(idRichiesta);
        if (visitaDTO == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Visita non trovata con ID: " + idRichiesta)
                    .build();
        }
        assegnazioneBadgeService.terminaUltimaAssegnazionePerPersona(visitaDTO.getVisitatore().getId());
        return Response.ok(visitaDTO).build();
    }
}
