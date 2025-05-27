package com.secondomona.persistence.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "AssegnazioniBadge")
public class AssegnazioneBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAssegnazione")
    private Integer idAssegnazione;

    @ManyToOne
    @JoinColumn(name = "IdTessera", nullable = false)
    private Tessera tessera;

    @ManyToOne
    @JoinColumn(name = "IdPersona", nullable = false)
    private Persona persona;

    @Column(name = "DataInizio", nullable = false)
    private LocalDateTime dataInizio;

    @Column(name = "DataFine")
    private LocalDateTime dataFine;

    public AssegnazioneBadge() {}

    public AssegnazioneBadge(Tessera tessera, Persona persona, LocalDateTime dataInizio, LocalDateTime dataFine) {
        this.tessera = tessera;
        this.persona = persona;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public Integer getIdAssegnazione() {
        return idAssegnazione;
    }

    public void setIdAssegnazione(Integer idAssegnazione) {
        this.idAssegnazione = idAssegnazione;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
}
