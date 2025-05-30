package com.secondomona.service;

import com.secondomona.dto.PersonaDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Servizio per la gestione della sicurezza e delle emergenze
 */
@ApplicationScoped
public class SicurezzaService {

    @Inject
    VisitaService visitaService;

    @Inject
    TimbraturaDipendenteService timbraturaDipendenteService;

    @Inject
    PdfService pdfService;

    /**
     * Ottiene l'elenco completo di tutte le persone attualmente presenti in azienda
     * (sia dipendenti che visitatori esterni) e genera un documento PDF
     *
     * @return Array di byte contenente il PDF con l'elenco delle persone presenti
     * @throws IOException Se si verifica un errore durante la generazione del PDF
     */
    public byte[] generaElencoPresentiFisiciPdf() throws IOException {
        List<PersonaDTO> personePresenti = new ArrayList<>();

        // Ottiene l'elenco dei dipendenti presenti
        personePresenti.addAll(timbraturaDipendenteService.getDipendentiPresenti());

        // Ottiene l'elenco dei visitatori presenti
        personePresenti.addAll(visitaService.getVisitatoriattualiPresenti());

        // Ordina l'elenco per cognome e poi per nome
        personePresenti.sort(Comparator.comparing(PersonaDTO::getCognome)
                .thenComparing(PersonaDTO::getNome));

        // Genera il PDF con l'elenco delle persone presenti
        return pdfService.generaElencoPresenzePdf(personePresenti);
    }
}
