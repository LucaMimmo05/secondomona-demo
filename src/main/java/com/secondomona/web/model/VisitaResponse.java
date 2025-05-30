package com.secondomona.web.model;

import com.secondomona.persistence.model.MaterialeInformatico;
import com.secondomona.persistence.model.Persona;

import java.time.LocalDateTime;

public class VisitaResponse {
    private Integer idRichiesta;

    private Persona visitatore;

    private Persona richiedente;

    private LocalDateTime dataInizio;

    private LocalDateTime dataFine;

    private String motivoVisita;

    private Boolean flagAccessoAutomezzo;

    private Boolean flagRichiestaDpi;

    private MaterialeInformatico materialInformatico;
}
