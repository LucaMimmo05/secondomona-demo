package com.secondomona.web;

import com.secondomona.service.SicurezzaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Path("/api/sicurezza")
@RolesAllowed({"refresh-token", "access-token"})
public class SicurezzaResource {

    @Inject
    SicurezzaService sicurezzaService;

    /**
     * Endpoint per generare un PDF con l'elenco delle persone presenti in azienda
     *
     * @return Un PDF con l'elenco delle persone presenti in azienda
     */
    @GET
    @Path("/presenze/pdf")
    @Produces("application/pdf")
    public Response generaElencoPresentiFisiciPdf() {
        try {
            byte[] pdfBytes = sicurezzaService.generaElencoPresentiFisiciPdf();

            // Formatta la data attuale per il nome del file
            String dataOra = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String nomeFile = "Presenze_" + dataOra + ".pdf";

            // Imposta gli header della risposta per il download del file
            return Response
                    .ok((StreamingOutput) output -> output.write(pdfBytes))
                    .header("Content-Disposition", "attachment; filename=\"" + nomeFile + "\"")
                    .header("Content-Type", "application/pdf")
                    .build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Errore nella generazione del PDF: " + e.getMessage())
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }
}
