package com.secondomona.web;

import com.secondomona.dto.AssegnazioneBadgeDTO;
import com.secondomona.service.AssegnazioneBadgeService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/badge")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BadgeResource {

    @Inject
    AssegnazioneBadgeService assegnazioneBadgeService;

    @GET
    @RolesAllowed({"Portineria", "Admin"})
    public Response getAllAssegnazioni() {
        List<AssegnazioneBadgeDTO> assegnazioni = assegnazioneBadgeService.getAllAssegnazioni();
        return Response.ok(assegnazioni).build();
    }

    @GET
    @Path("/{id}")
    public Response getAssegnazioneById(@PathParam("id") Long id) {
        AssegnazioneBadgeDTO dto = assegnazioneBadgeService.getAssegnazioneById(id);
        return Response.ok(dto).build();
    }

    @POST
    @Path("/assegna")
    public Response assegnaBadge(AssegnazioneBadgeDTO dto) {
        AssegnazioneBadgeDTO result = assegnazioneBadgeService.assegnaBadge(dto);
        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    @PUT
    @Path("/termina/{id}")
    public Response terminaAssegnazione(@PathParam("id") Long id) {
        AssegnazioneBadgeDTO result = assegnazioneBadgeService.terminaAssegnazione(id);
        return Response.ok(result).build();
    }

    @GET
    @Path("/persona/{idPersona}")
    public Response getAssegnazioniByPersona(@PathParam("idPersona") Long idPersona) {
        List<AssegnazioneBadgeDTO> assegnazioni = assegnazioneBadgeService.getAssegnazioniByPersona(idPersona);
        return Response.ok(assegnazioni).build();
    }
}
