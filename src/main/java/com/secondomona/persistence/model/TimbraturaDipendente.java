package com.secondomona.persistence.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Timbrature")  // Modificato il nome della tabella da TimbratureDipendenti a Timbrature
public class TimbraturaDipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTimbratura")
    private Integer idTimbratura;

    @ManyToOne
    @JoinColumn(name = "IdTessera", nullable = false)
    private Tessera tessera;

    @Column(name = "DataOraTimbratura", nullable = false)
    private LocalDateTime dataOraTimbratura;

    @Column(name = "TipoTimbratura", nullable = false, length = 20)
    private String tipoTimbratura; // "ENTRATA" o "USCITA"

    @Column(name = "Note", length = 255)
    private String note;

    @Column(name = "Validata", nullable = false)
    private Boolean validata = false;

    @Column(name = "ValidataDa")
    private Integer validataDa;

    @Column(name = "DataValidazione")
    private LocalDateTime dataValidazione;

    // Costruttori
    public TimbraturaDipendente() {}

    public TimbraturaDipendente(Tessera tessera, LocalDateTime dataOraTimbratura, String tipoTimbratura) {
        this.tessera = tessera;
        this.dataOraTimbratura = dataOraTimbratura;
        this.tipoTimbratura = tipoTimbratura;
    }

    // Getters e Setters
    public Integer getIdTimbratura() {
        return idTimbratura;
    }

    public void setIdTimbratura(Integer idTimbratura) {
        this.idTimbratura = idTimbratura;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    // Metodo di convenienza per ottenere la persona dalla tessera
    public Persona getPersona() {
        return tessera != null ? tessera.getPersona() : null;
    }

    public LocalDateTime getDataOraTimbratura() {
        return dataOraTimbratura;
    }

    public void setDataOraTimbratura(LocalDateTime dataOraTimbratura) {
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

    public Integer getValidataDa() {
        return validataDa;
    }

    public void setValidataDa(Integer validataDa) {
        this.validataDa = validataDa;
    }

    public LocalDateTime getDataValidazione() {
        return dataValidazione;
    }

    public void setDataValidazione(LocalDateTime dataValidazione) {
        this.dataValidazione = dataValidazione;
    }
}
