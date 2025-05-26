package com.secondomona.service;

import com.secondomona.persistence.PersonaRepository;
import com.secondomona.persistence.model.Persona;
import com.secondomona.web.model.PersonaRequest;
import com.secondomona.web.model.PersonaResponse;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public PersonaResponse authenticate(String email, String password) {
        Persona persona = personaRepository.authenticate(email, password);
        if (persona == null) {
            return null;
        }
        return toPersonaResponse(persona);
    }

    public List<Persona> getAllPersonas() {

        return personaRepository.findAll().list();
    }

    @Transactional
    public PersonaResponse createPersona(PersonaRequest personaRequest) {
        String passwordHash = BcryptUtil.bcryptHash(personaRequest.getPassword());
        Persona persona = new Persona(
                personaRequest.getIdRuna(),
                personaRequest.getNome(),
                personaRequest.getCognome(),
                personaRequest.getDiminutivo(),
                personaRequest.getAzienda(),
                personaRequest.getIndirizzo(),
                personaRequest.getCitta(),
                personaRequest.getProvincia(),
                personaRequest.getNazione(),
                personaRequest.getTelefono(),
                personaRequest.getCellulare(),
                personaRequest.getFax(),
                personaRequest.getpIva(),
                personaRequest.getCf(),
                personaRequest.getMail(),
                personaRequest.getFoto(),
                personaRequest.getDataAssunzione(),
                personaRequest.getMatricola(),
                personaRequest.getIdFiliale(),
                personaRequest.getIdMansione(),
                personaRequest.getIdDeposito(),
                personaRequest.getIdRiferimento(),
                personaRequest.getVisitatore(),
                personaRequest.getAccessNumber(),
                personaRequest.getAccessCount(),
                personaRequest.getAccessUpdate(),
                personaRequest.getLuogoNascita(),
                personaRequest.getDataNascita(),
                personaRequest.getDataScadCertificato(),
                personaRequest.getPreposto(),
                personaRequest.getAntincendio(),
                personaRequest.getPrimoSoccorso(),
                personaRequest.getTipoDocumento(),
                personaRequest.getNumeroDocumento(),
                personaRequest.getDataScadenzaDocumento(),
                personaRequest.getDuvri(),
                personaRequest.getFlagPrivacy(),
                personaRequest.getDataConsegnaPrivacy(),
                personaRequest.getCentroCosto(),
                personaRequest.getRuolo().toString(),
                passwordHash
        );
        personaRepository.persist(persona);
        return toPersonaResponse(persona);
    }

    public PersonaResponse getPersonaByEmail(String email) {
        return toPersonaResponse(personaRepository.findByEmail(email));
    }

    private PersonaResponse toPersonaResponse(Persona persona) {
        return new PersonaResponse(
                persona.getIdPersona(),
                persona.getNome(),
                persona.getCognome(),
                persona.getMail(),
                persona.getRuolo()
        );
    }
}
