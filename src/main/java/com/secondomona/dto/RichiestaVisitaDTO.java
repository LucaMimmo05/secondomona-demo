package com.secondomona.dto;

import com.secondomona.persistence.model.MaterialeInformatico;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class RichiestaVisitaDTO {
    private Integer idRichiesta;
    private PersonaDTO visitatore;
    private PersonaDTO richiedente;
    private OffsetDateTime dataInizio;
    private OffsetDateTime dataFine;
    private String motivoVisita;
    private boolean flagAccessoAutomezzo;
    private boolean flagRichiestaDPI;
    private MaterialeInformatico materialeInformatico;

    public RichiestaVisitaDTO(Integer idRichiesta, PersonaDTO visitatore, PersonaDTO richiedente, OffsetDateTime dataInizio, OffsetDateTime dataFine, String motivoVisita, Boolean flagAccessoAutomezzo, Boolean flagRichiestaDpi, MaterialeInformatico materialeInformatico) {
        this.idRichiesta = idRichiesta;
        this.visitatore = visitatore;
        this.richiedente = richiedente;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.motivoVisita = motivoVisita;
        this.flagAccessoAutomezzo = flagAccessoAutomezzo;
        this.flagRichiestaDPI = flagRichiestaDpi;
        this.materialeInformatico = materialeInformatico;
    }


    public RichiestaVisitaDTO() {

    }

    public Integer getIdRichiesta() {
        return idRichiesta;
    }

    public void setIdRichiesta(Integer idRichiesta) {
        this.idRichiesta = idRichiesta;
    }

    public PersonaDTO getVisitatore() {
        return visitatore;
    }

    public void setVisitatore(PersonaDTO visitatore) {
        this.visitatore = visitatore;
    }

    public PersonaDTO getRichiedente() {
        return richiedente;
    }

    public void setRichiedente(PersonaDTO richiedente) {
        this.richiedente = richiedente;
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

    public String getMotivoVisita() {
        return motivoVisita;
    }

    public void setMotivoVisita(String motivoVisita) {
        this.motivoVisita = motivoVisita;
    }

    public boolean isFlagAccessoAutomezzo() {
        return flagAccessoAutomezzo;
    }

    public void setFlagAccessoAutomezzo(boolean flagAccessoAutomezzo) {
        this.flagAccessoAutomezzo = flagAccessoAutomezzo;
    }

    public boolean isFlagRichiestaDPI() {
        return flagRichiestaDPI;
    }

    public void setFlagRichiestaDPI(boolean flagRichiestaDPI) {
        this.flagRichiestaDPI = flagRichiestaDPI;
    }

    public MaterialeInformatico getMaterialeInformatico() {
        return materialeInformatico;
    }

    public void setMaterialeInformatico(MaterialeInformatico materialeInformatico) {
        this.materialeInformatico = materialeInformatico;
    }
}
