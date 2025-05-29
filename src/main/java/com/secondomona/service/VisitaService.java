package com.secondomona.service;

import com.secondomona.dto.PersonaDTO;
import com.secondomona.dto.RichiestaVisitaDTO;
import com.secondomona.persistence.VisitaRepository;
import com.secondomona.persistence.model.RichiestaVisita;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

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
                                visita.getVisitatore().getMail(),
                                visita.getVisitatore().getIdPersona()
                        ),
                        new PersonaDTO(
                                visita.getRichiedente().getNome(),
                                visita.getRichiedente().getCognome(),
                                visita.getRichiedente().getMail(),
                                visita.getVisitatore().getIdPersona()
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

    public List<RichiestaVisitaDTO> getVisiteInAttesa() {
        return visitaRepository.findVisiteInAttesa().stream()
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
        visitatoreDto.setNome(entity.getVisitatore().getNome());
        visitatoreDto.setCognome(entity.getVisitatore().getCognome());
        visitatoreDto.setMail(entity.getVisitatore().getMail());
        visitatoreDto.setIdPersona(entity.getVisitatore().getIdPersona());
        dto.setVisitatore(visitatoreDto);

        PersonaDTO richiedenteDto = new PersonaDTO();
        richiedenteDto.setNome(entity.getRichiedente().getNome());
        richiedenteDto.setCognome(entity.getRichiedente().getCognome());
        richiedenteDto.setMail(entity.getRichiedente().getMail());
        dto.setRichiedente(richiedenteDto);

        return dto;
    }

    public RichiestaVisita createRichiestaVisita(RichiestaVisita entity) {
        return visitaRepository.createVisita(entity);
    }

    /**
     * Conclude una visita impostando la data di fine al momento attuale
     *
     * @param idRichiesta l'ID della richiesta di visita da concludere
     * @return La richiesta di visita aggiornata come DTO o null se non trovata
     */
    @Transactional
    public RichiestaVisitaDTO concludiVisita(Integer idRichiesta) {
        Optional<RichiestaVisita> visitaOptional = visitaRepository.concludiVisita(idRichiesta);
        return visitaOptional.map(this::toDTO).orElse(null);
    }

    /**
     * Ottiene la lista di tutti i visitatori attualmente presenti in azienda
     *
     * @return Lista di PersonaDTO con i dati dei visitatori presenti
     */
    public List<PersonaDTO> getVisitatoriattualiPresenti() {
        return visitaRepository.findVisiteAttive().stream()
                .map(visita -> {
                    PersonaDTO visitatore = new PersonaDTO();
                    visitatore.setIdPersona(visita.getVisitatore().getIdPersona());
                    visitatore.setNome(visita.getVisitatore().getNome());
                    visitatore.setCognome(visita.getVisitatore().getCognome());
                    visitatore.setMail(visita.getVisitatore().getMail());
                    return visitatore;
                })
                .toList();
    }

}
