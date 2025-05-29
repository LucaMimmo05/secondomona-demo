package com.secondomona.service;

import com.secondomona.dto.TesseraDTO;
import com.secondomona.persistence.PersonaRepository;
import com.secondomona.persistence.TesseraRepository;
import com.secondomona.persistence.model.Persona;
import com.secondomona.persistence.model.Tessera;
import com.secondomona.persistence.model.roles.Ruolo;
import com.secondomona.web.model.PersonaRequest;
import com.secondomona.web.model.PersonaResponse;
import com.secondomona.web.model.VisitatoreRequest;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class PersonaService {

    private final PersonaRepository personaRepository;
    private final TesseraRepository tesseraRepository;

    public PersonaService(PersonaRepository personaRepository, TesseraRepository tesseraRepository) {
        this.personaRepository = personaRepository;
        this.tesseraRepository = tesseraRepository;
    }

    public PersonaResponse authenticate(String email, String password) {
        Persona persona = personaRepository.authenticate(email, password);
        if (persona == null) {
            return null;
        }
        return toPersonaResponse(persona);
    }

    public List<Persona> getAllDipendenti() {

        return personaRepository.getDipendenti();
    }

    @Transactional
    public PersonaResponse createDipendenti(PersonaRequest personaRequest) {
        // Verifica se l'email è già utilizzata
        Persona existingPersona = personaRepository.findByEmail(personaRequest.getMail());
        if (existingPersona != null) {
            throw new WebApplicationException("Email già in uso", 400);
        }

        // Verifica se il numero di documento è già utilizzato
        existingPersona = personaRepository.findByNumeroDocumento(personaRequest.getNumeroDocumento());
        if (existingPersona != null) {
            throw new WebApplicationException("Numero documento già in uso", 400);
        }

        // Verifica che la password non sia null o vuota
        if (personaRequest.getPassword() == null || personaRequest.getPassword().isEmpty()) {
            throw new WebApplicationException("La password non può essere vuota", 400);
        }

        // Genera un valore di matricola se non fornito
        String matricola = personaRequest.getMatricola();
        if (matricola == null || matricola.isEmpty()) {
            // Genera una matricola casuale
            matricola = "MAT-" + LocalDate.now().getYear() + "-" + new Random().nextInt(10000);
        }

        String passwordHash = BcryptUtil.bcryptHash(personaRequest.getPassword());

        // Inizializza i valori predefiniti per i campi obbligatori che potrebbero essere null
        Boolean visitatore = personaRequest.getVisitatore() != null ? personaRequest.getVisitatore() : false;
        Integer accessNumber = personaRequest.getAccessNumber() != null ? personaRequest.getAccessNumber() : 0;
        Integer accessCount = personaRequest.getAccessCount() != null ? personaRequest.getAccessCount() : 0;
        LocalDateTime accessUpdate = personaRequest.getAccessUpdate() != null ?
                personaRequest.getAccessUpdate() : LocalDateTime.now();
        Boolean preposto = personaRequest.getPreposto() != null ? personaRequest.getPreposto() : false;
        Boolean antincendio = personaRequest.getAntincendio() != null ? personaRequest.getAntincendio() : false;
        Boolean primoSoccorso = personaRequest.getPrimoSoccorso() != null ? personaRequest.getPrimoSoccorso() : false;
        Boolean duvri = personaRequest.getDuvri() != null ? personaRequest.getDuvri() : false;
        Boolean flagPrivacy = personaRequest.getFlagPrivacy() != null ? personaRequest.getFlagPrivacy() : false;

        // Verifica che il centro di costo esista e sia valido
        if (personaRequest.getCentroCosto() == null || personaRequest.getCentroCosto().getIdCentroCosto() == null ||
                personaRequest.getCentroCosto().getIdCentroCosto() <= 0) {
            throw new WebApplicationException("Centro di costo non valido o non specificato", 400);
        }

        // Verifica l'esistenza del centro di costo nel database
        try {
            // Qui potremmo aggiungere una verifica più esplicita se necessario
            // Ad esempio, cercando il centro di costo nel database
        } catch (Exception e) {
            throw new WebApplicationException("Centro di costo non trovato nel database: " + e.getMessage(), 400);
        }

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
                matricola,
                personaRequest.getIdFiliale(),
                personaRequest.getIdMansione(),
                personaRequest.getIdDeposito(),
                personaRequest.getIdRiferimento(),
                visitatore,
                accessNumber,
                accessCount,
                accessUpdate,
                personaRequest.getLuogoNascita(),
                personaRequest.getDataNascita(),
                personaRequest.getDataScadCertificato(),
                preposto,
                antincendio,
                primoSoccorso,
                personaRequest.getTipoDocumento(),
                personaRequest.getNumeroDocumento(),
                personaRequest.getDataScadenzaDocumento(),
                duvri,
                flagPrivacy,
                personaRequest.getDataConsegnaPrivacy(),
                personaRequest.getCentroCosto(),
                personaRequest.getRuolo().toString(),
                passwordHash
        );
        personaRepository.persist(persona);
        String code = LocalDate.now().toString() + "-" + new Random().nextInt(1000);
        TesseraDTO tesseraDTO = new TesseraDTO(
                persona,
                1,
                "Dipendente",
                "TESS-" + code,
                "EXT-" + code,
                true,
                OffsetDateTime.now(),
                null,
                true,
                false,
                true,
                null,
                null,
                null,
                1234,
                "ATTIVO",
                "STANDARD"
        );
        tesseraRepository.persist(tesseraDTO.toTessera());
        return toPersonaResponse(persona);
    }

    public PersonaResponse getPersonaByEmail(String email) {
        return toPersonaResponse(personaRepository.findByEmail(email));
    }

    /**
     * Recupera una persona tramite il suo ID
     * @param id ID della persona da recuperare
     * @return PersonaResponse con i dati della persona, o null se non trovata
     */
    public PersonaResponse getPersonaById(Long id) {
        Persona persona = personaRepository.findById(id);
        return toPersonaResponse(persona);
    }

    /**
     * Recupera una persona completa tramite il suo ID
     * @param id ID della persona da recuperare
     * @return Persona con tutti i dati, o null se non trovata
     */
    public Persona getFullPersonaById(Long id) {
        return personaRepository.findById(id);
    }

    /**
     * Recupera la tessera attiva associata a una persona
     * @param persona La persona di cui recuperare la tessera
     * @return La tessera attiva, o null se non esiste
     */
    private Tessera getTesseraAttivaByPersona(Persona persona) {
        if (persona == null) {
            return null;
        }

        // Cerchiamo una tessera attiva per questa persona
        // Usando find con Panache
        Tessera tessera = tesseraRepository.find("persona = ?1 AND abilitata = true AND attivata = true AND eliminata = false", persona)
                .firstResult();

        return tessera;
    }

    private PersonaResponse toPersonaResponse(Persona persona) {
        if (persona == null) {
            return null;
        }

        // Recupera la tessera attiva della persona
        Tessera tessera = getTesseraAttivaByPersona(persona);
        Long idTessera = (tessera != null) ? tessera.getIdTessera().longValue() : null;

        return new PersonaResponse(
                persona.getIdPersona(),
                persona.getNome(),
                persona.getCognome(),
                persona.getMail(),
                persona.getRuolo(),
                idTessera
        );
    }

    public List<Persona> getDipendenti() {
        return personaRepository.getDipendenti();
    }

    public List<Persona> getVisitatori() {
        return personaRepository.getVisitatori();
    }

    public Persona createVisitatore(VisitatoreRequest request) {
        // Verifico se esiste già una persona con lo stesso numero di documento
        if (request.getNumeroDocumento() != null && !request.getNumeroDocumento().isEmpty()) {
            Persona existingPersona = personaRepository.findByNumeroDocumento(request.getNumeroDocumento());
            if (existingPersona != null) {
                // Se esiste già, lancio un'eccezione personalizzata
                throw new com.secondomona.service.exception.DocumentoGiaEsistenteException(request.getNumeroDocumento());
            }
        }

        Persona persona = new Persona();

        persona.setIdRuna(request.getIdRuna());
        persona.setNome(request.getNome());
        persona.setCognome(request.getCognome());
        persona.setDiminutivo(request.getDiminutivo());
        persona.setAzienda(request.getAzienda());
        persona.setIndirizzo(request.getIndirizzo());
        persona.setCitta(request.getCitta());
        persona.setProvincia(request.getProvincia());
        persona.setNazione(request.getNazione());
        persona.setTelefono(request.getTelefono());
        persona.setCellulare(request.getCellulare());
        persona.setFax(request.getFax());
        persona.setpIva(request.getpIva());
        persona.setCf(request.getCf());
        persona.setMail(request.getMail());
        persona.setFoto(request.getFoto());
        persona.setDataAssunzione(request.getDataAssunzione());
        persona.setMatricola(request.getMatricola());
        persona.setIdFiliale(request.getIdFiliale());
        persona.setIdMansione(request.getIdMansione());
        persona.setIdDeposito(request.getIdDeposito());
        persona.setIdRiferimento(request.getIdRiferimento());

        // Flag specifici per il visitatore
        persona.setVisitatore(true);
        persona.setPreposto(false);
        persona.setAntincendio(false);
        persona.setPrimoSoccorso(false);

        // Ruolo come enum
        persona.setRuolo(Ruolo.Visitatore.name());

        // Altri campi
        persona.setTipoDocumento(request.getTipoDocumento());
        persona.setNumeroDocumento(request.getNumeroDocumento());
        persona.setDataScadenzaDocumento(request.getDataScadenzaDocumento());
        persona.setDuvri(request.getDuvri());
        persona.setFlagPrivacy(request.getFlagPrivacy());
        persona.setDataConsegnaPrivacy(request.getDataConsegnaPrivacy());
        persona.setCentroCosto(request.getCentroCosto());
        persona.setPasswordHash(null);

        return personaRepository.save(persona);
    }
}
