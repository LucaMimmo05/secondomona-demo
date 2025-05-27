package com.secondomona.web;

import com.secondomona.dto.TimbraturaDipendenteDTO;
import com.secondomona.service.TimbraturaDipendenteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/api/timbrature")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TimbraturaDipendenteResource {

    @Inject
    TimbraturaDipendenteService timbraturaDipendenteService;

    @GET
    public Response getAllTimbrature() {
        List<TimbraturaDipendenteDTO> timbrature = timbraturaDipendenteService.getAllTimbrature();
        return Response.ok(timbrature).build();
    }

    @GET
    @Path("/{id}")
    public Response getTimbratura(@PathParam("id") Long id) {
        TimbraturaDipendenteDTO timbratura = timbraturaDipendenteService.getTimbratura(id);
        return Response.ok(timbratura).build();
    }

    @POST
    @RolesAllowed({"access-token", "refresh-token"})
    public Response registraTimbratura(TimbraturaDipendenteDTO timbratura) {
        TimbraturaDipendenteDTO result = timbraturaDipendenteService.registraTimbratura(timbratura);
        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    @PUT
    @Path("/{id}/valida")
    public Response validaTimbratura(@PathParam("id") Long id, @QueryParam("validatorId") Long validatorId) {
        if (validatorId == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("È necessario fornire l'ID del validatore")
                           .build();
        }

        TimbraturaDipendenteDTO result = timbraturaDipendenteService.validaTimbratura(id, validatorId);
        return Response.ok(result).build();
    }

    @GET
    @Path("/persona/{idPersona}")
    public Response getTimbratureByPersona(@PathParam("idPersona") Long idPersona) {
        List<TimbraturaDipendenteDTO> timbrature = timbraturaDipendenteService.getTimbratureByPersona(idPersona);
        return Response.ok(timbrature).build();
    }

    @GET
    @Path("/data/{data}")
    public Response getTimbratureByDate(@PathParam("data") String dataStr) {
        try {
            LocalDate data = LocalDate.parse(dataStr, DateTimeFormatter.ISO_DATE);
            List<TimbraturaDipendenteDTO> timbrature = timbraturaDipendenteService.getTimbratureByDate(data);
            return Response.ok(timbrature).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Formato data non valido. Utilizzare il formato ISO (YYYY-MM-DD)")
                           .build();
        }
    }

    @GET
    @Path("/non-validate")
    public Response getTimbratureNonValidate() {
        List<TimbraturaDipendenteDTO> timbrature = timbraturaDipendenteService.getTimbratureNonValidate();
        return Response.ok(timbrature).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTimbratura(@PathParam("id") Long id) {
        timbraturaDipendenteService.deleteTimbratura(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/oggi/{idPersona}")
    @RolesAllowed({"access-token", "refresh-token"})
    public Response getTimbratureOdierneUtente(@PathParam("idPersona") Long idPersona) {
        try {
            List<TimbraturaDipendenteDTO> timbrature = timbraturaDipendenteService.getTimbratureOdierneByPersona(idPersona);
            return Response.ok(timbrature).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Persona non trovata con ID: " + idPersona)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Errore durante il recupero delle timbrature: " + e.getMessage())
                    .build();
        }
    }
}
