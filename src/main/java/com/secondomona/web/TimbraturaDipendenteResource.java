package com.secondomona.web;

import com.secondomona.dto.TimbraturaDipendenteDTO;
import com.secondomona.service.TimbraturaDipendenteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

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
    public Response registraTimbratura(Map<String, Object> datiTimbratura) {
        try {
            // Estrai i dati dalla mappa
            Long idTessera = ((Number) datiTimbratura.get("idTessera")).longValue();
            String tipoTimbratura = (String) datiTimbratura.get("tipoTimbratura");
            String note = (String) datiTimbratura.get("note");

            // Gestione speciale per la data/ora
            String dataOraStr = (String) datiTimbratura.get("dataOraTimbratura");
            OffsetDateTime dataOra;

            if (dataOraStr != null) {
                try {
                    // Prima prova a parsare con offset
                    dataOra = OffsetDateTime.parse(dataOraStr);
                } catch (DateTimeParseException e) {
                    // Se fallisce, interpreta come LocalDateTime e aggiungi offset
                    LocalDateTime localDateTime = LocalDateTime.parse(dataOraStr);
                    dataOra = localDateTime.atOffset(ZoneOffset.systemDefault().getRules().getOffset(localDateTime));
                }
            } else {
                dataOra = OffsetDateTime.now();
            }

            // Crea e popola il DTO
            TimbraturaDipendenteDTO dto = new TimbraturaDipendenteDTO();
            dto.setIdTessera(idTessera);
            dto.setTipoTimbratura(tipoTimbratura);
            dto.setDataOraTimbratura(dataOra);
            dto.setNote(note);

            // Esegui la timbratura
            TimbraturaDipendenteDTO result = timbraturaDipendenteService.registraTimbratura(dto);
            return Response.status(Response.Status.CREATED).entity(result).build();
        } catch (Exception e) {
            // Log dettagliato dell'errore
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Errore nella registrazione della timbratura: " + e.getMessage())
                .build();
        }
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
