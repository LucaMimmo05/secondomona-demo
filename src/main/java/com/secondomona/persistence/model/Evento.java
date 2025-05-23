package com.secondomona.persistence.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Eventi")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEvento")
    private Long id;

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
}
