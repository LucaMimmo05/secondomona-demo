package com.secondomona.dto;

import java.time.OffsetDateTime;

public class TimbraturaDipendenteDTO {
    private Long idTimbratura;
    private Long idPersona;
    private Long idTessera;
    private OffsetDateTime dataOraTimbratura;
    private String tipoTimbratura;
    private String note;
    private Boolean validata;
    private Long validataDa;
    private OffsetDateTime dataValidazione;

    // Campi aggiuntivi per le risposte
    private String nomeDipendente;
    private String cognomeDipendente;
    private String codiceTessera;

    public TimbraturaDipendenteDTO() {}

    // Getters e Setters
    public Long getIdTimbratura() {
        return idTimbratura;
    }

    public void setIdTimbratura(Long idTimbratura) {
        this.idTimbratura = idTimbratura;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public Long getIdTessera() {
        return idTessera;
    }

    public void setIdTessera(Long idTessera) {
        this.idTessera = idTessera;
    }

    public OffsetDateTime getDataOraTimbratura() {
        return dataOraTimbratura;
    }

    public void setDataOraTimbratura(OffsetDateTime dataOraTimbratura) {
        this.dataOraTimbratura = dataOraTimbratura;
    }

    public String getTipoTimbratura() {
        return tipoTimbratura;
    }

    public void setTipoTimbratura(String tipoTimbratura) {
        this.tipoTimbratura = tipoTimbratura;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getValidata() {
        return validata;
    }

    public void setValidata(Boolean validata) {
        this.validata = validata;
    }

    public Long getValidataDa() {
        return validataDa;
    }

    public void setValidataDa(Long validataDa) {
        this.validataDa = validataDa;
    }

    public OffsetDateTime getDataValidazione() {
        return dataValidazione;
    }

    public void setDataValidazione(OffsetDateTime dataValidazione) {
        this.dataValidazione = dataValidazione;
    }

    public String getNomeDipendente() {
        return nomeDipendente;
    }

    public void setNomeDipendente(String nomeDipendente) {
        this.nomeDipendente = nomeDipendente;
    }

    public String getCognomeDipendente() {
        return cognomeDipendente;
    }

    public void setCognomeDipendente(String cognomeDipendente) {
        this.cognomeDipendente = cognomeDipendente;
    }

    public String getCodiceTessera() {
        return codiceTessera;
    }

    public void setCodiceTessera(String codiceTessera) {
        this.codiceTessera = codiceTessera;
    }
}
