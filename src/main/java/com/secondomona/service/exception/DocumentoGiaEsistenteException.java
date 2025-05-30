package com.secondomona.service.exception;

import jakarta.ws.rs.BadRequestException;

public class DocumentoGiaEsistenteException extends BadRequestException {

    public DocumentoGiaEsistenteException(String numeroDocumento) {
        super("Esiste già una persona con il numero di documento: " + numeroDocumento);
    }
}
