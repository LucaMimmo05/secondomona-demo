package com.secondomona.service;

import com.secondomona.dto.AssegnazioneBadgeDTO;
import com.secondomona.dto.PersonaDTO;
import com.secondomona.persistence.AssegnazioneBadgeRepository;
import com.secondomona.persistence.PersonaRepository;
import com.secondomona.persistence.TesseraRepository;
import com.secondomona.persistence.model.AssegnazioneBadge;
import com.secondomona.persistence.model.Persona;
import com.secondomona.persistence.model.Tessera;
import com.secondomona.web.PersonaResource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AssegnazioneBadgeService {

    AssegnazioneBadgeRepository assegnazioneBadgeRepository;
    TesseraRepository tesseraRepository;
    PersonaRepository personaRepository;

    public AssegnazioneBadgeService(AssegnazioneBadgeRepository assegnazioneBadgeRepository, TesseraRepository tesseraRepository, PersonaRepository personaRepository) {
        this.assegnazioneBadgeRepository = assegnazioneBadgeRepository;
        this.tesseraRepository = tesseraRepository;
        this.personaRepository = personaRepository;
    }

    public List<AssegnazioneBadgeDTO> getAllAssegnazioni() {
        return assegnazioneBadgeRepository.listAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public AssegnazioneBadgeDTO getAssegnazioneById(Long id) {
        AssegnazioneBadge assegnazione = assegnazioneBadgeRepository.findById(id);
        if (assegnazione == null) {
            throw new NotFoundException("Assegnazione badge non trovata con ID: " + id);
        }
        return mapToDTO(assegnazione);
    }

    @Transactional
    public AssegnazioneBadgeDTO terminaAssegnazione(Long id) {
        AssegnazioneBadge assegnazione = assegnazioneBadgeRepository.findById(id);
        if (assegnazione == null) {
            throw new NotFoundException("Assegnazione badge non trovata con ID: " + id);
        }
        assegnazione.setDataFine(LocalDateTime.now());
        assegnazioneBadgeRepository.persist(assegnazione);
        return mapToDTO(assegnazione);
    }

    public List<AssegnazioneBadgeDTO> getAssegnazioniByPersona(Long idPersona) {
        Persona persona = personaRepository.findById(idPersona);
        if (persona == null) {
            throw new NotFoundException("Persona non trovata con ID: " + idPersona);
        }
        return assegnazioneBadgeRepository.findByPersona(persona).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private AssegnazioneBadgeDTO mapToDTO(AssegnazioneBadge entity) {
        AssegnazioneBadgeDTO dto = new AssegnazioneBadgeDTO();

        dto.setIdAssegnazione(entity.getIdAssegnazione() != null ? entity.getIdAssegnazione().longValue() : null);

        dto.setIdTessera(entity.getTessera().getIdTessera() != null ? entity.getTessera().getIdTessera().longValue() : null);

        dto.setIdPersona(entity.getPersona().getIdPersona() != null ? entity.getPersona().getIdPersona().longValue() : null);

        dto.setDataInizio(entity.getDataInizio());
        dto.setDataFine(entity.getDataFine());
        dto.setNumeroTessera(entity.getTessera().getCodiceTessera());

        // Mappa i dati della persona
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setNome(entity.getPersona().getNome());
        personaDTO.setCognome(entity.getPersona().getCognome());
        personaDTO.setMail(entity.getPersona().getMail());

        dto.setPersona(personaDTO);

        return dto;
    }

    public AssegnazioneBadge assegnazioneBadge(Persona persona) {
        return assegnazioneBadgeRepository.assegnaBadge(persona);
    }

    @Transactional
    public void terminaUltimaAssegnazionePerPersona(Long idPersona) {
        Persona persona = personaRepository.findById(idPersona);
        if (persona == null) {
            throw new NotFoundException("Persona non trovata con ID: " + idPersona);
        }
        List<AssegnazioneBadge> assegnazioni = assegnazioneBadgeRepository.findByPersona(persona);
        assegnazioni.stream()
                .filter(a -> a.getDataFine() == null)
                .max((a1, a2) -> a1.getDataInizio().compareTo(a2.getDataInizio()))
                .ifPresent(a -> {
                    a.setDataFine(LocalDateTime.now());
                    assegnazioneBadgeRepository.persist(a);
                });
    }
}

