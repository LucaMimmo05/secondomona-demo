package com.secondomona.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MaterialiInformatici")
public class MaterialeInformatico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMaterialeInformatico")
    private Integer idMaterialeInformatico;

    @Column(name = "Tipologia", nullable = false, length = 50)
    private String tipologia;

    @Column(name = "Marca", length = 100)
    private String marca;

    @Column(name = "Seriale", length = 100)
    private String seriale;

    @Column(name = "Note")
    private String note;

    public MaterialeInformatico() {}

    public MaterialeInformatico(String tipologia, String marca, String seriale, String note) {
        this.tipologia = tipologia;
        this.marca = marca;
        this.seriale = seriale;
        this.note = note;
    }

    public Integer getIdMaterialeInformatico() {
        return idMaterialeInformatico;
    }

    public void setIdMaterialeInformatico(Integer idMaterialeInformatico) {
        this.idMaterialeInformatico = idMaterialeInformatico;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSeriale() {
        return seriale;
    }

    public void setSeriale(String seriale) {
        this.seriale = seriale;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
