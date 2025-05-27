package com.secondomona.web.model;

public class PersonaResponse {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String role;
    private Long idTessera; // Nuovo campo per l'ID della tessera

    public PersonaResponse(long id, String name, String surname, String email, String role, Long idTessera) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.email = email;
        this.role = role;
        this.idTessera = idTessera;
    }

    // Costruttore senza idTessera per retrocompatibilità
    public PersonaResponse(long id, String name, String surname, String email, String role) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.email = email;
        this.role = role;
        this.idTessera = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getIdTessera() {
        return idTessera;
    }

    public void setIdTessera(Long idTessera) {
        this.idTessera = idTessera;
    }
}
