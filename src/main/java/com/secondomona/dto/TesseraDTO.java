package com.secondomona.dto;

import com.secondomona.persistence.model.Persona;
import com.secondomona.persistence.model.Tessera;

import java.time.OffsetDateTime;

public class TesseraDTO {

    private Persona persona;
    private int idCategoria;
    private String categoria;
    private String codiceTessera;
    private String codiceEsterno;
    private boolean abilitata;
    private OffsetDateTime dataInizio;
    private OffsetDateTime dataFine;
    private boolean attivata;
    private boolean eliminata;
    private boolean reqPresenza;
    private OffsetDateTime dataRestituzione;
    private Integer idRestituzione;
    private String apb;
    private int pin;
    private String apbState;
    private String tipologiaBadge;

    public TesseraDTO(Persona persona, int idCategoria, String categoria, String codiceTessera, String codiceEsterno, boolean abilitata, OffsetDateTime dataInizio, OffsetDateTime dataFine, boolean attivata, boolean eliminata, boolean reqPresenza, OffsetDateTime dataRestituzione, Integer idRestituzione, String apb, int pin, String apbState, String tipologiaBadge) {
        this.persona = persona;
        this.idCategoria = idCategoria;
        this.categoria = categoria;
        this.codiceTessera = codiceTessera;
        this.codiceEsterno = codiceEsterno;
        this.abilitata = abilitata;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.attivata = attivata;
        this.eliminata = eliminata;
        this.reqPresenza = reqPresenza;
        this.dataRestituzione = dataRestituzione;
        this.idRestituzione = idRestituzione;
        this.apb = apb;
        this.pin = pin;
        this.apbState = apbState;
        this.tipologiaBadge = tipologiaBadge;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCodiceTessera() {
        return codiceTessera;
    }

    public void setCodiceTessera(String codiceTessera) {
        this.codiceTessera = codiceTessera;
    }

    public String getCodiceEsterno() {
        return codiceEsterno;
    }

    public void setCodiceEsterno(String codiceEsterno) {
        this.codiceEsterno = codiceEsterno;
    }

    public boolean isAbilitata() {
        return abilitata;
    }

    public void setAbilitata(boolean abilitata) {
        this.abilitata = abilitata;
    }

    public OffsetDateTime getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(OffsetDateTime dataInizio) {
        this.dataInizio = dataInizio;
    }

    public OffsetDateTime getDataFine() {
        return dataFine;
    }

    public void setDataFine(OffsetDateTime dataFine) {
        this.dataFine = dataFine;
    }

    public boolean isAttivata() {
        return attivata;
    }

    public void setAttivata(boolean attivata) {
        this.attivata = attivata;
    }

    public boolean isEliminata() {
        return eliminata;
    }

    public void setEliminata(boolean eliminata) {
        this.eliminata = eliminata;
    }

    public boolean isReqPresenza() {
        return reqPresenza;
    }

    public void setReqPresenza(boolean reqPresenza) {
        this.reqPresenza = reqPresenza;
    }

    public OffsetDateTime getDataRestituzione() {
        return dataRestituzione;
    }

    public void setDataRestituzione(OffsetDateTime dataRestituzione) {
        this.dataRestituzione = dataRestituzione;
    }

    public Integer getIdRestituzione() {
        return idRestituzione;
    }

    public void setIdRestituzione(Integer idRestituzione) {
        this.idRestituzione = idRestituzione;
    }

    public String getApb() {
        return apb;
    }

    public void setApb(String apb) {
        this.apb = apb;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getApbState() {
        return apbState;
    }

    public void setApbState(String apbState) {
        this.apbState = apbState;
    }

    public String getTipologiaBadge() {
        return tipologiaBadge;
    }

    public void setTipologiaBadge(String tipologiaBadge) {
        this.tipologiaBadge = tipologiaBadge;
    }

    public Tessera toTessera() {
        return new Tessera(persona, idCategoria, categoria, codiceTessera, codiceEsterno, abilitata, dataInizio != null ? dataInizio.toLocalDateTime() : null, dataFine != null ? dataFine.toLocalDateTime() : null, dataFine != null, attivata, eliminata, reqPresenza, dataRestituzione != null ? dataRestituzione.toLocalDateTime() : null, idRestituzione, apb, String.valueOf(pin), apbState, tipologiaBadge);
    }
}
