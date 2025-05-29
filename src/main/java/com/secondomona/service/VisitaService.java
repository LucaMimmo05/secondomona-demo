package com.secondomona.service;

import com.secondomona.dto.PersonaDTO;
import com.secondomona.dto.RichiestaVisitaDTO;
import com.secondomona.persistence.PersonaRepository;
import com.secondomona.persistence.VisitaRepository;
import com.secondomona.persistence.model.Persona;
import com.secondomona.persistence.model.RichiestaVisita;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;

import java.util.List;

@ApplicationScoped
public class VisitaService {

    private final VisitaRepository visitaRepository;
    private final PersonaRepository personaRepository;

    public VisitaService(VisitaRepository visitaRepository, PersonaRepository personaRepository) {
        this.visitaRepository = visitaRepository;
        this.personaRepository = personaRepository;
    }

    public List<RichiestaVisitaDTO> getAllRichiesteVisite() {
        List<RichiestaVisita> richieste = visitaRepository.findAll().list();

        return richieste.stream().map(this::toDTO).toList();
    }

    public List<RichiestaVisitaDTO> getVisiteAttive() {
        return visitaRepository.findVisiteAttive().stream().map(visita -> new RichiestaVisitaDTO(visita.getIdRichiesta(), new PersonaDTO(visita.getVisitatore().getIdPersona(), visita.getVisitatore().getNome(), visita.getVisitatore().getCognome(), visita.getVisitatore().getMail()), new PersonaDTO(visita.getRichiedente().getIdPersona(), visita.getRichiedente().getNome(), visita.getRichiedente().getCognome(), visita.getRichiedente().getMail()), visita.getDataInizio(), visita.getDataFine(), visita.getMotivoVisita(), visita.getFlagAccessoAutomezzo(), visita.getFlagRichiestaDpi(), visita.getMaterialeInformatico())).toList();
    }

    public List<RichiestaVisitaDTO> getVisiteInAttesa() {
        return visitaRepository.findVisiteInAttesa().stream().map(visita -> new RichiestaVisitaDTO(visita.getIdRichiesta(), new PersonaDTO(visita.getVisitatore().getIdPersona(), visita.getVisitatore().getNome(), visita.getVisitatore().getCognome(), visita.getVisitatore().getMail()), new PersonaDTO(visita.getRichiedente().getIdPersona(), visita.getRichiedente().getNome(), visita.getRichiedente().getCognome(), visita.getRichiedente().getMail()), visita.getDataInizio(), visita.getDataFine(), visita.getMotivoVisita(), visita.getFlagAccessoAutomezzo(), visita.getFlagRichiestaDpi(), visita.getMaterialeInformatico())).toList();
    }


    public RichiestaVisitaDTO toDTO(RichiestaVisita entity) {
        RichiestaVisitaDTO dto = new RichiestaVisitaDTO();
        dto.setIdRichiesta(entity.getIdRichiesta());
        dto.setDataInizio(entity.getDataInizio());
        dto.setDataFine(entity.getDataFine());
        dto.setMotivoVisita(entity.getMotivoVisita());

        // Mappa solo i dati che vuoi di Persona
        PersonaDTO visitatoreDto = new PersonaDTO();
        visitatoreDto.setNome(entity.getVisitatore().getNome());
        visitatoreDto.setCognome(entity.getVisitatore().getCognome());
        visitatoreDto.setMail(entity.getVisitatore().getMail());
        dto.setVisitatore(visitatoreDto);

        PersonaDTO richiedenteDto = new PersonaDTO();
        richiedenteDto.setNome(entity.getRichiedente().getNome());
        richiedenteDto.setCognome(entity.getRichiedente().getCognome());
        richiedenteDto.setMail(entity.getRichiedente().getMail());
        dto.setRichiedente(richiedenteDto);

        return dto;
    }

    @Transactional
    public RichiestaVisita createRichiestaVisitaFromDTO(RichiestaVisitaDTO dto) {
        RichiestaVisita visita = new RichiestaVisita();
        Persona richiedente = personaRepository.findById(dto.getRichiedente().getId());
        Persona visitatore = personaRepository.findById(dto.getVisitatore().getId());
        if (richiedente == null || visitatore == null) {
            throw new WebApplicationException("Persona non trovata", 400);
        }
        visita.setRichiedente(richiedente);
        visita.setVisitatore(visitatore);
        visita.setDataInizio(dto.getDataInizio());
        visita.setDataFine(dto.getDataFine());
        visita.setMotivoVisita(dto.getMotivoVisita());
        visita.setFlagAccessoAutomezzo(dto.isFlagAccessoAutomezzo());
        visita.setFlagRichiestaDpi(dto.isFlagRichiestaDPI());
        visita.setMaterialeInformatico(dto.getMaterialeInformatico());
        visitaRepository.persist(visita);
        return visita;
    }
}
