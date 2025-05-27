package com.secondomona.dto;

import java.time.LocalDateTime;

public class AssegnazioneBadgeDTO {

    private Long idAssegnazione;
    private Long idTessera;
    private Long idPersona;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;

    // Dati aggiuntivi per la risposta
    private String numeroTessera;
    private PersonaDTO persona;

    public AssegnazioneBadgeDTO() {}

    // Getter e Setter

    public Long getIdAssegnazione() {
        return idAssegnazione;
    }

    public void setIdAssegnazione(Long idAssegnazione) {
        this.idAssegnazione = idAssegnazione;
    }

    public Long getIdTessera() {
        return idTessera;
    }

    public void setIdTessera(Long idTessera) {
        this.idTessera = idTessera;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public LocalDateTime getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDateTime dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDateTime getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDateTime dataFine) {
        this.dataFine = dataFine;
    }

    public String getNumeroTessera() {
        return numeroTessera;
    }

    public void setNumeroTessera(String numeroTessera) {
        this.numeroTessera = numeroTessera;
    }

    public PersonaDTO getPersona() {
        return persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }
}
