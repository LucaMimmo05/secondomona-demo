package com.secondomona.persistence.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Eventi")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEvento")
    private Long idEvento;

    @Column(name = "IdApparato")
    private Integer idApparato;

    @Column(name = "IdAreaApparato")
    private Integer idAreaApparato;

    @Column(name = "IdDispositivo")
    private Integer idDispositivo;

    @Column(name = "CodiceEvento", length = 50)
    private String codiceEvento;

    @ManyToOne
    @JoinColumn(name = "IdUtente", nullable = false)
    private Persona utente;

    @Column(name = "NomePC", length = 100)
    private String nomePC;

    @Column(name = "Persona", length = 200)
    private String personaTestuale;

    @ManyToOne
    @JoinColumn(name = "IdTessera")
    private Tessera tessera;

    @Column(name = "CodiceTessera", length = 100)
    private String codiceTessera;

    @Column(name = "CodiceEsterno", length = 100)
    private String codiceEsterno;

    @Column(name = "CodiceSito", length = 100)
    private String codiceSito;

    @Column(name = "Azienda", length = 200)
    private String azienda;

    @Column(name = "Giustificativo")
    private String giustificativo;

    @Column(name = "PersonaRiferimento", length = 200)
    private String personaRiferimento;

    @Column(name = "IdRiferimento")
    private Integer idRiferimento;

    @Column(name = "CF", length = 16)
    private String cf;

    @Column(name = "pIva", length = 20)
    private String pIva;

    @Column(name = "ImgLicencePlate", length = 500)
    private String imgLicencePlate;

    @Column(name = "ImgEnvironment", length = 500)
    private String imgEnvironment;

    @Column(name = "DataEvento", nullable = false)
    private LocalDateTime dataEvento;

    @Column(name = "DataRegistrazione", nullable = false)
    private LocalDateTime dataRegistrazione = LocalDateTime.now();

    @Column(name = "DataLastChange", nullable = false)
    private LocalDateTime dataLastChange = LocalDateTime.now();

    @Column(name = "IdEventoVisonic")
    private Long idEventoVisonic;

    public Evento() {}

    public Evento(Integer idApparato, Integer idAreaApparato, Integer idDispositivo, String codiceEvento, Persona utente, String nomePC, String personaTestuale, Tessera tessera, String codiceTessera, String codiceEsterno, String codiceSito, String azienda, String giustificativo, String personaRiferimento, Integer idRiferimento, String cf, String pIva, String imgLicencePlate, String imgEnvironment, LocalDateTime dataEvento, LocalDateTime dataRegistrazione, LocalDateTime dataLastChange, Long idEventoVisonic) {
        this.idApparato = idApparato;
        this.idAreaApparato = idAreaApparato;
        this.idDispositivo = idDispositivo;
        this.codiceEvento = codiceEvento;
        this.utente = utente;
        this.nomePC = nomePC;
        this.personaTestuale = personaTestuale;
        this.tessera = tessera;
        this.codiceTessera = codiceTessera;
        this.codiceEsterno = codiceEsterno;
        this.codiceSito = codiceSito;
        this.azienda = azienda;
        this.giustificativo = giustificativo;
        this.personaRiferimento = personaRiferimento;
        this.idRiferimento = idRiferimento;
        this.cf = cf;
        this.pIva = pIva;
        this.imgLicencePlate = imgLicencePlate;
        this.imgEnvironment = imgEnvironment;
        this.dataEvento = dataEvento;
        this.dataRegistrazione = dataRegistrazione;
        this.dataLastChange = dataLastChange;
        this.idEventoVisonic = idEventoVisonic;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public Integer getIdApparato() {
        return idApparato;
    }

    public void setIdApparato(Integer idApparato) {
        this.idApparato = idApparato;
    }

    public Integer getIdAreaApparato() {
        return idAreaApparato;
    }

    public void setIdAreaApparato(Integer idAreaApparato) {
        this.idAreaApparato = idAreaApparato;
    }

    public Integer getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(Integer idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getCodiceEvento() {
        return codiceEvento;
    }

    public void setCodiceEvento(String codiceEvento) {
        this.codiceEvento = codiceEvento;
    }

    public Persona getUtente() {
        return utente;
    }

    public void setUtente(Persona utente) {
        this.utente = utente;
    }

    public String getNomePC() {
        return nomePC;
    }

    public void setNomePC(String nomePC) {
        this.nomePC = nomePC;
    }

    public String getPersonaTestuale() {
        return personaTestuale;
    }

    public void setPersonaTestuale(String personaTestuale) {
        this.personaTestuale = personaTestuale;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
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

    public String getCodiceSito() {
        return codiceSito;
    }

    public void setCodiceSito(String codiceSito) {
        this.codiceSito = codiceSito;
    }

    public String getAzienda() {
        return azienda;
    }

    public void setAzienda(String azienda) {
        this.azienda = azienda;
    }

    public String getGiustificativo() {
        return giustificativo;
    }

    public void setGiustificativo(String giustificativo) {
        this.giustificativo = giustificativo;
    }

    public String getPersonaRiferimento() {
        return personaRiferimento;
    }

    public void setPersonaRiferimento(String personaRiferimento) {
        this.personaRiferimento = personaRiferimento;
    }

    public Integer getIdRiferimento() {
        return idRiferimento;
    }

    public void setIdRiferimento(Integer idRiferimento) {
        this.idRiferimento = idRiferimento;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getpIva() {
        return pIva;
    }

    public void setpIva(String pIva) {
        this.pIva = pIva;
    }

    public String getImgLicencePlate() {
        return imgLicencePlate;
    }

    public void setImgLicencePlate(String imgLicencePlate) {
        this.imgLicencePlate = imgLicencePlate;
    }

    public String getImgEnvironment() {
        return imgEnvironment;
    }

    public void setImgEnvironment(String imgEnvironment) {
        this.imgEnvironment = imgEnvironment;
    }

    public LocalDateTime getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDateTime dataEvento) {
        this.dataEvento = dataEvento;
    }

    public LocalDateTime getDataRegistrazione() {
        return dataRegistrazione;
    }

    public void setDataRegistrazione(LocalDateTime dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
    }

    public LocalDateTime getDataLastChange() {
        return dataLastChange;
    }

    public void setDataLastChange(LocalDateTime dataLastChange) {
        this.dataLastChange = dataLastChange;
    }

    public Long getIdEventoVisonic() {
        return idEventoVisonic;
    }

    public void setIdEventoVisonic(Long idEventoVisonic) {
        this.idEventoVisonic = idEventoVisonic;
    }
}
