package com.secondomona.web.exception;

import com.secondomona.service.exception.DocumentoGiaEsistenteException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DocumentoGiaEsistenteExceptionMapper implements ExceptionMapper<DocumentoGiaEsistenteException> {

    @Override
    public Response toResponse(DocumentoGiaEsistenteException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                Response.Status.BAD_REQUEST.getStatusCode(),
                "DOCUMENTO_DUPLICATO",
                exception.getMessage()
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .build();
    }
}
