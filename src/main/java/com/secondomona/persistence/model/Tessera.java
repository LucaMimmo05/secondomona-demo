package com.secondomona.persistence.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Tessere")
public class Tessera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTessera")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IdPersona", nullable = false)
    private Persona persona;

    @Column(name = "IdCategoria")
    private Integer idCategoria;

    @Column(name = "Categorie", length = 200)
    private String categorie;

    @Column(name = "CodiceTessera", nullable = false, length = 100, unique = true)
    private String codiceTessera;

    @Column(name = "CodiceEsterno", nullable = false, length = 100, unique = true)
    private String codiceEsterno;

    @Column(name = "Abilitata", nullable = false)
    private Boolean abilitata = true;

    @Column(name = "DataInizio")
    private LocalDateTime dataInizio;

    @Column(name = "DataFine")
    private LocalDateTime dataFine;

    @Column(name = "ConScadenza", nullable = false)
    private Boolean conScadenza = false;

    @Column(name = "Attivata", nullable = false)
    private Boolean attivata = true;

    @Column(name = "Eliminata", nullable = false)
    private Boolean eliminata = false;

    @Column(name = "RegPresenza", nullable = false)
    private Boolean regPresenza = false;

    @Column(name = "DataRestituzione")
    private LocalDateTime dataRestituzione;

    @Column(name = "IdRestituzione")
    private Integer idRestituzione;

    @Column(name = "Apb", length = 50)
    private String apb;

    @Column(name = "Pin", length = 20)
    private String pin;

    @Column(name = "ApbState", length = 50)
    private String apbState;

    @Column(name = "TipologiaBadge", nullable = false, length = 50)
    private String tipologiaBadge;

    public Tessera() {}

    public Tessera(Persona persona, Integer idCategoria, String categorie, String codiceTessera, String codiceEsterno, Boolean abilitata, LocalDateTime dataInizio, LocalDateTime dataFine, Boolean conScadenza, Boolean attivata, Boolean eliminata, Boolean regPresenza, LocalDateTime dataRestituzione, Integer idRestituzione, String apb, String pin, String apbState, String tipologiaBadge) {
        this.persona = persona;
        this.idCategoria = idCategoria;
        this.categorie = categorie;
        this.codiceTessera = codiceTessera;
        this.codiceEsterno = codiceEsterno;
        this.abilitata = abilitata;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.conScadenza = conScadenza;
        this.attivata = attivata;
        this.eliminata = eliminata;
        this.regPresenza = regPresenza;
        this.dataRestituzione = dataRestituzione;
        this.idRestituzione = idRestituzione;
        this.apb = apb;
        this.pin = pin;
        this.apbState = apbState;
        this.tipologiaBadge = tipologiaBadge;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
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

    public Boolean getAbilitata() {
        return abilitata;
    }

    public void setAbilitata(Boolean abilitata) {
        this.abilitata = abilitata;
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

    public Boolean getConScadenza() {
        return conScadenza;
    }

    public void setConScadenza(Boolean conScadenza) {
        this.conScadenza = conScadenza;
    }

    public Boolean getAttivata() {
        return attivata;
    }

    public void setAttivata(Boolean attivata) {
        this.attivata = attivata;
    }

    public Boolean getEliminata() {
        return eliminata;
    }

    public void setEliminata(Boolean eliminata) {
        this.eliminata = eliminata;
    }

    public Boolean getRegPresenza() {
        return regPresenza;
    }

    public void setRegPresenza(Boolean regPresenza) {
        this.regPresenza = regPresenza;
    }

    public LocalDateTime getDataRestituzione() {
        return dataRestituzione;
    }

    public void setDataRestituzione(LocalDateTime dataRestituzione) {
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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
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
}
