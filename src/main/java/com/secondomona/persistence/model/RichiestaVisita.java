package com.secondomona.persistence.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "RichiesteVisite")
public class RichiestaVisita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdRichiesta")
    private Integer idRichiesta;

    @ManyToOne
    @JoinColumn(name = "IdPersonaVisitatore", nullable = false)
    private Persona visitatore;

    @ManyToOne
    @JoinColumn(name = "IdPersonaRichiedente", nullable = false)
    private Persona richiedente;

    @Column(name = "DataInizio", nullable = false)
    private LocalDateTime dataInizio;

    @Column(name = "DataFine", nullable = false)
    private LocalDateTime dataFine;

    @Column(name = "MotivoVisita")
    private String motivoVisita;

    @Column(name = "FlagAccessoAutomezzo", nullable = false)
    private Boolean flagAccessoAutomezzo = false;

    @Column(name = "FlagRichiestaDPI", nullable = false)
    private Boolean flagRichiestaDpi = false;

    @ManyToOne
    @JoinColumn(name = "IdMaterialeInformatico")
    private MaterialeInformatico materialeInformatico;

    public RichiestaVisita() {}

    public RichiestaVisita(Persona visitatore, Persona richiedente, LocalDateTime dataInizio, LocalDateTime dataFine, String motivoVisita, Boolean flagAccessoAutomezzo, Boolean flagRichiestaDpi, MaterialeInformatico materialeInformatico) {
        this.visitatore = visitatore;
        this.richiedente = richiedente;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.motivoVisita = motivoVisita;
        this.flagAccessoAutomezzo = flagAccessoAutomezzo;
        this.flagRichiestaDpi = flagRichiestaDpi;
        this.materialeInformatico = materialeInformatico;
    }

    public Integer getIdRichiesta() {
        return idRichiesta;
    }

    public void setIdRichiesta(Integer idRichiesta) {
        this.idRichiesta = idRichiesta;
    }

    public Persona getVisitatore() {
        return visitatore;
    }

    public void setVisitatore(Persona visitatore) {
        this.visitatore = visitatore;
    }

    public Persona getRichiedente() {
        return richiedente;
    }

    public void setRichiedente(Persona richiedente) {
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

    public Boolean getFlagAccessoAutomezzo() {
        return flagAccessoAutomezzo;
    }

    public void setFlagAccessoAutomezzo(Boolean flagAccessoAutomezzo) {
        this.flagAccessoAutomezzo = flagAccessoAutomezzo;
    }

    public Boolean getFlagRichiestaDpi() {
        return flagRichiestaDpi;
    }

    public void setFlagRichiestaDpi(Boolean flagRichiestaDpi) {
        this.flagRichiestaDpi = flagRichiestaDpi;
    }

    public MaterialeInformatico getMaterialeInformatico() {
        return materialeInformatico;
    }

    public void setMaterialeInformatico(MaterialeInformatico materialeInformatico) {
        this.materialeInformatico = materialeInformatico;
    }
}
