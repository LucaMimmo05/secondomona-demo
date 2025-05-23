package com.secondomona.dto;

import com.secondomona.persistence.model.MaterialeInformatico;

import java.time.LocalDateTime;

public class RichiestaVisitaDTO {
    private Integer idRichiesta;
    private PersonaDTO visitatore;
    private PersonaDTO richiedente;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String motivoVisita;
    private boolean flagAccessoAutomezzo;
    private boolean flagRichiestaDPI;
    private MaterialeInformatico materialeInformatico;

    public RichiestaVisitaDTO(Integer idRichiesta, PersonaDTO personeDTO, PersonaDTO dto, LocalDateTime dataInizio, LocalDateTime dataFine, String motivoVisita, Boolean flagAccessoAutomezzo, Boolean flagRichiestaDpi) {
        this.idRichiesta = idRichiesta;
        this.visitatore = personeDTO;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.motivoVisita = motivoVisita;
        this.flagAccessoAutomezzo = flagAccessoAutomezzo;
        this.flagRichiestaDPI = flagRichiestaDpi;
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
