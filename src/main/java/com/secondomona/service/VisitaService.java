package com.secondomona.service;

import com.secondomona.dto.PersonaDTO;
import com.secondomona.dto.RichiestaVisitaDTO;
import com.secondomona.persistence.VisitaRepository;
import com.secondomona.persistence.model.RichiestaVisita;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class VisitaService {

    private final VisitaRepository visitaRepository;

    public VisitaService(VisitaRepository visitaRepository) {
        this.visitaRepository = visitaRepository;
    }

    public List<RichiestaVisitaDTO> getAllRichiesteVisite() {
        List<RichiestaVisita> richieste = visitaRepository.findAll().list();

        return richieste.stream()
                .map(this::toDTO)
                .toList();
    }

    public RichiestaVisitaDTO toDTO(RichiestaVisita entity) {
        RichiestaVisitaDTO dto = new RichiestaVisitaDTO();
        dto.setIdRichiesta(entity.getIdRichiesta());
        dto.setDataInizio(entity.getDataInizio());
        dto.setDataFine(entity.getDataFine());
        dto.setMotivoVisita(entity.getMotivoVisita());

        // Mappa solo i dati che vuoi di Persona
        PersonaDTO visitatoreDto = new PersonaDTO();
        visitatoreDto.setName(entity.getVisitatore().getNome());
        visitatoreDto.setSurname(entity.getVisitatore().getCognome());
        visitatoreDto.setEmail(entity.getVisitatore().getMail());
        dto.setVisitatore(visitatoreDto);

        PersonaDTO richiedenteDto = new PersonaDTO();
        richiedenteDto.setName(entity.getRichiedente().getNome());
        richiedenteDto.setSurname(entity.getRichiedente().getCognome());
        richiedenteDto.setEmail(entity.getRichiedente().getMail());
        dto.setRichiedente(richiedenteDto);

        return dto;
    }


}
