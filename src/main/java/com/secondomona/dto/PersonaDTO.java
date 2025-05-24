package com.secondomona.dto;

public class PersonaDTO {
    private String nome;
    private String cognome;
    private String mail;

    public PersonaDTO(String nomeVisitatore, String cognomeVisitatore, String emailVisitatore) {
        this.nome = nomeVisitatore;
        this.cognome = cognomeVisitatore;
        this.mail = emailVisitatore;
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

}
