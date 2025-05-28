package com.secondomona.dto;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

public class PersonaDTO {
    private String nome;
    private String cognome;
    private String mail;
    private Integer idPersona;

    public PersonaDTO(String nomeVisitatore, String cognomeVisitatore, String emailVisitatore, Integer idPersona) {
        this.nome = nomeVisitatore;
        this.cognome = cognomeVisitatore;
        this.mail = emailVisitatore;
        this.idPersona = idPersona;
    }

    public PersonaDTO() {

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getIdPersona() { return idPersona; }

    public void setIdPersona(Integer idPersona) { this.idPersona = idPersona; }

}
