package com.secondomona.web.model;

public class PersonaResponse {

    private long id;
    private String email;
    private String role;

    public PersonaResponse(long id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
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
}
