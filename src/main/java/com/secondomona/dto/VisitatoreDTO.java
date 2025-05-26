package com.secondomona.dto;

public class VisitatoreDTO {

    private String first_name;
    private String last_name;
    private String company;
    private String email;

    public VisitatoreDTO() {}

    public VisitatoreDTO(String first_name, String last_name, String company, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.company = company;
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }
}
