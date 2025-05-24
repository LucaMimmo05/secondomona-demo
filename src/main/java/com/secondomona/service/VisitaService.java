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

    public List<RichiestaVisitaDTO> getVisiteAttive() {
        return visitaRepository.findVisiteAttive().stream()
                .map(visita -> new RichiestaVisitaDTO(
                        visita.getIdRichiesta(),
                        new PersonaDTO(
                                visita.getVisitatore().getNome(),
                                visita.getVisitatore().getCognome(),
                                visita.getVisitatore().getMail()
                        ),
                        new PersonaDTO(
                                visita.getRichiedente().getNome(),
                                visita.getRichiedente().getCognome(),
                                visita.getRichiedente().getMail()
                        ),
                        visita.getDataInizio(),
                        visita.getDataFine(),
                        visita.getMotivoVisita(),
                        visita.getFlagAccessoAutomezzo(),
                        visita.getFlagRichiestaDpi(),
                        visita.getMaterialeInformatico()
                ))
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


}
