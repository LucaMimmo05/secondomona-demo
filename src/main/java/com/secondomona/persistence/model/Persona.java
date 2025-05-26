package com.secondomona.persistence.model;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Persone")
@UserDefinition
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPersona")
    private Integer idPersona;

    @Column(name = "IdRuna", length = 50)
    private String idRuna;

    @Column(name = "Nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "Cognome", nullable = false, length = 100)
    private String cognome;

    @Column(name = "Diminutivo", length = 50)
    private String diminutivo;

    @Column(name = "Azienda", length = 200)
    private String azienda;

    @Column(name = "Indirizzo", length = 200)
    private String indirizzo;

    @Column(name = "Citta", length = 100)
    private String citta;

    @Column(name = "Provincia", length = 50)
    private String provincia;

    @Column(name = "Nazione", length = 100)
    private String nazione;

    @Column(name = "Telefono", length = 50)
    private String telefono;

    @Column(name = "Cellulare", length = 50)
    private String cellulare;

    @Column(name = "Fax", length = 50)
    private String fax;

    @Column(name = "pIva", length = 20)
    private String pIva;

    @Column(name = "CF", nullable = false, length = 16, unique = true)
    private String cf;

    @Username
    @Column(name = "Mail", nullable = false, length = 200, unique = true)
    private String mail;

    @Column(name = "Foto", length = 500)
    private String foto;

    @Column(name = "DataAssunzione")
    private LocalDate dataAssunzione;

    @Column(name = "Matricola", length = 50, unique = true)
    private String matricola;

    @Column(name = "IdFiliale")
    private Integer idFiliale;

    @Column(name = "IdMansione")
    private Integer idMansione;

    @Column(name = "IdDeposito")
    private Integer idDeposito;

    @Column(name = "IdRiferimento")
    private Integer idRiferimento;

    @Column(name = "Visitatore", nullable = false)
    private Boolean visitatore;

    @Column(name = "AccessNumber", nullable = false)
    private Integer accessNumber = 0;

    @Column(name = "AccessCount", nullable = false)
    private Integer accessCount = 0;

    @Column(name = "AccessUpdate")
    private LocalDateTime accessUpdate = LocalDateTime.now();

    @Column(name = "LuogoNascita", length = 100)
    private String luogoNascita;

    @Column(name = "DataNascita")
    private LocalDate dataNascita;

    @Column(name = "DataScadCertificato")
    private LocalDate dataScadCertificato;

    @Column(name = "Preposto", nullable = false)
    private Boolean preposto = false;

    @Column(name = "Antincendio", nullable = false)
    private Boolean antincendio = false;

    @Column(name = "PrimoSoccorso", nullable = false)
    private Boolean primoSoccorso = false;

    @Column(name = "TipoDocumento", length = 50)
    private String tipoDocumento;

    @Column(name = "NumeroDocumento", nullable = false, length = 100, unique = true)
    private String numeroDocumento;

    @Column(name = "DataScadenzaDocumento", nullable = false)
    private LocalDate dataScadenzaDocumento;

    @Column(name = "Duvri", nullable = false)
    private Boolean duvri = false;

    @Column(name = "FlagPrivacy", nullable = false)
    private Boolean flagPrivacy = false;

    @Column(name = "DataConsegnaPrivacy")
    private LocalDate dataConsegnaPrivacy;

    @ManyToOne
    @JoinColumn(name = "IdCentroCosto", nullable = false)
    private CentroCosto centroCosto;

    @Roles
    @Column(name = "Ruolo", nullable = false, length = 20)
    private String ruolo;

    @Password
    @Column(name = "PasswordHash", nullable = false, length = 255)
    private String passwordHash;

    public Persona() {}

    public Persona(String idRuna, String nome, String cognome, String diminutivo, String azienda, String indirizzo, String citta, String provincia, String nazione, String telefono, String cellulare, String fax, String pIva, String cf, String mail, String foto, LocalDate dataAssunzione, String matricola, Integer idFiliale, Integer idMansione, Integer idDeposito, Integer idRiferimento, Boolean visitatore, Integer accessNumber, Integer accessCount, LocalDateTime accessUpdate, String luogoNascita, LocalDate dataNascita, LocalDate dataScadCertificato, Boolean preposto, Boolean antincendio, Boolean primoSoccorso, String tipoDocumento, String numeroDocumento, LocalDate dataScadenzaDocumento, Boolean duvri, Boolean flagPrivacy, LocalDate dataConsegnaPrivacy, CentroCosto centroCosto, String ruolo, String passwordHash) {
        this.idRuna = idRuna;
        this.nome = nome;
        this.cognome = cognome;
        this.diminutivo = diminutivo;
        this.azienda = azienda;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.provincia = provincia;
        this.nazione = nazione;
        this.telefono = telefono;
        this.cellulare = cellulare;
        this.fax = fax;
        this.pIva = pIva;
        this.cf = cf;
        this.mail = mail;
        this.foto = foto;
        this.dataAssunzione = dataAssunzione;
        this.matricola = matricola;
        this.idFiliale = idFiliale;
        this.idMansione = idMansione;
        this.idDeposito = idDeposito;
        this.idRiferimento = idRiferimento;
        this.visitatore = visitatore;
        this.accessNumber = accessNumber;
        this.accessCount = accessCount;
        this.accessUpdate = accessUpdate;
        this.luogoNascita = luogoNascita;
        this.dataNascita = dataNascita;
        this.dataScadCertificato = dataScadCertificato;
        this.preposto = preposto;
        this.antincendio = antincendio;
        this.primoSoccorso = primoSoccorso;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.dataScadenzaDocumento = dataScadenzaDocumento;
        this.duvri = duvri;
        this.flagPrivacy = flagPrivacy;
        this.dataConsegnaPrivacy = dataConsegnaPrivacy;
        this.centroCosto = centroCosto;
        this.ruolo = ruolo;
        this.passwordHash = passwordHash;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getIdRuna() {
        return idRuna;
    }

    public void setIdRuna(String idRuna) {
        this.idRuna = idRuna;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getDiminutivo() {
        return diminutivo;
    }

    public void setDiminutivo(String diminutivo) {
        this.diminutivo = diminutivo;
    }

    public String getAzienda() {
        return azienda;
    }

    public void setAzienda(String azienda) {
        this.azienda = azienda;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getpIva() {
        return pIva;
    }

    public void setpIva(String pIva) {
        this.pIva = pIva;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public LocalDate getDataAssunzione() {
        return dataAssunzione;
    }

    public void setDataAssunzione(LocalDate dataAssunzione) {
        this.dataAssunzione = dataAssunzione;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public Integer getIdFiliale() {
        return idFiliale;
    }

    public void setIdFiliale(Integer idFiliale) {
        this.idFiliale = idFiliale;
    }

    public Integer getIdMansione() {
        return idMansione;
    }

    public void setIdMansione(Integer idMansione) {
        this.idMansione = idMansione;
    }

    public Integer getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(Integer idDeposito) {
        this.idDeposito = idDeposito;
    }

    public Integer getIdRiferimento() {
        return idRiferimento;
    }

    public void setIdRiferimento(Integer idRiferimento) {
        this.idRiferimento = idRiferimento;
    }

    public Boolean getVisitatore() {
        return visitatore;
    }

    public void setVisitatore(Boolean visitatore) {
        this.visitatore = visitatore;
    }

    public Integer getAccessNumber() {
        return accessNumber;
    }

    public void setAccessNumber(Integer accessNumber) {
        this.accessNumber = accessNumber;
    }

    public Integer getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(Integer accessCount) {
        this.accessCount = accessCount;
    }

    public LocalDateTime getAccessUpdate() {
        return accessUpdate;
    }

    public void setAccessUpdate(LocalDateTime accessUpdate) {
        this.accessUpdate = accessUpdate;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public LocalDate getDataScadCertificato() {
        return dataScadCertificato;
    }

    public void setDataScadCertificato(LocalDate dataScadCertificato) {
        this.dataScadCertificato = dataScadCertificato;
    }

    public Boolean getPreposto() {
        return preposto;
    }

    public void setPreposto(Boolean preposto) {
        this.preposto = preposto;
    }

    public Boolean getAntincendio() {
        return antincendio;
    }

    public void setAntincendio(Boolean antincendio) {
        this.antincendio = antincendio;
    }

    public Boolean getPrimoSoccorso() {
        return primoSoccorso;
    }

    public void setPrimoSoccorso(Boolean primoSoccorso) {
        this.primoSoccorso = primoSoccorso;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public LocalDate getDataScadenzaDocumento() {
        return dataScadenzaDocumento;
    }

    public void setDataScadenzaDocumento(LocalDate dataScadenzaDocumento) {
        this.dataScadenzaDocumento = dataScadenzaDocumento;
    }

    public Boolean getDuvri() {
        return duvri;
    }

    public void setDuvri(Boolean duvri) {
        this.duvri = duvri;
    }

    public Boolean getFlagPrivacy() {
        return flagPrivacy;
    }

    public void setFlagPrivacy(Boolean flagPrivacy) {
        this.flagPrivacy = flagPrivacy;
    }

    public LocalDate getDataConsegnaPrivacy() {
        return dataConsegnaPrivacy;
    }

    public void setDataConsegnaPrivacy(LocalDate dataConsegnaPrivacy) {
        this.dataConsegnaPrivacy = dataConsegnaPrivacy;
    }

    public CentroCosto getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(CentroCosto centroCosto) {
        this.centroCosto = centroCosto;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
