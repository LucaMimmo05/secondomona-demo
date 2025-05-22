package com.secondomona.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CentriCosti")
public class CentroCosto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCentroCosto")
    private Integer idCentroCosto;

    @Column(name = "NumeroCentroCosto", nullable = false, length = 50)
    private String numeroCentroCosto;

    @Column(name = "DescrizioneCentroCosto", length = 200)
    private String descrizioneCentroCosto;

    public CentroCosto() {}

    public CentroCosto(Integer idCentroCosto, String numeroCentroCosto, String descrizioneCentroCosto) {
        this.idCentroCosto = idCentroCosto;
        this.numeroCentroCosto = numeroCentroCosto;
        this.descrizioneCentroCosto = descrizioneCentroCosto;
    }

    public Integer getIdCentroCosto() {
        return idCentroCosto;
    }

    public void setIdCentroCosto(Integer idCentroCosto) {
        this.idCentroCosto = idCentroCosto;
    }

    public String getNumeroCentroCosto() {
        return numeroCentroCosto;
    }

    public void setNumeroCentroCosto(String numeroCentroCosto) {
        this.numeroCentroCosto = numeroCentroCosto;
    }

    public String getDescrizioneCentroCosto() {
        return descrizioneCentroCosto;
    }

    public void setDescrizioneCentroCosto(String descrizioneCentroCosto) {
        this.descrizioneCentroCosto = descrizioneCentroCosto;
    }
}
