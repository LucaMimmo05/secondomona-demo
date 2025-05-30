package com.secondomona.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse {

    @JsonProperty("accessToken")
    private String accessToken;

    @JsonProperty("refreshToken")
    private String refreshToken;

    @JsonProperty("idTessera")
    private Long idTessera;  // ID della tessera

    @JsonProperty("idPersona")
    private Long idPersona;  // Nuovo campo per l'ID della persona

    public TokenResponse(String accessToken, String refreshToken, Long idTessera, Long idPersona) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.idTessera = idTessera;
        this.idPersona = idPersona;
    }

    public TokenResponse(String accessToken, String refreshToken, Long idTessera) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.idTessera = idTessera;
        this.idPersona = null;
    }

    // Costruttore senza idTessera e idPersona per retrocompatibilità
    public TokenResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.idTessera = null;
        this.idPersona = null;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getIdTessera() {
        return idTessera;
    }

    public void setIdTessera(Long idTessera) {
        this.idTessera = idTessera;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }
}
