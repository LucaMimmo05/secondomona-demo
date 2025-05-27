package com.secondomona.service;

import com.secondomona.dto.TimbraturaDipendenteDTO;
import com.secondomona.persistence.PersonaRepository;
import com.secondomona.persistence.TesseraRepository;
import com.secondomona.persistence.TimbraturaDipendenteRepository;
import com.secondomona.persistence.model.Persona;
import com.secondomona.persistence.model.Tessera;
import com.secondomona.persistence.model.TimbraturaDipendente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TimbraturaDipendenteService {

    @Inject
    TimbraturaDipendenteRepository timbraturaDipendenteRepository;

    @Inject
    PersonaRepository personaRepository;

    @Inject
    TesseraRepository tesseraRepository;

    public List<TimbraturaDipendenteDTO> getAllTimbrature() {
        return timbraturaDipendenteRepository.listAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TimbraturaDipendenteDTO getTimbratura(Long id) {
        TimbraturaDipendente timbratura = timbraturaDipendenteRepository.findById(id);
        if (timbratura == null) {
            throw new NotFoundException("Timbratura non trovata con ID: " + id);
        }
        return mapToDTO(timbratura);
    }

    @Transactional
    public TimbraturaDipendenteDTO registraTimbratura(TimbraturaDipendenteDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("I dati della timbratura non possono essere nulli");
        }

        // Recupero solo la tessera
        Tessera tessera = tesseraRepository.findById(dto.getIdTessera());
        if (tessera == null) {
            throw new NotFoundException("Tessera non trovata con ID: " + dto.getIdTessera());
        }

        // Determina automaticamente il tipo di timbratura (ENTRATA/USCITA) se non specificato
        if (dto.getTipoTimbratura() == null || dto.getTipoTimbratura().isEmpty()) {
            // Otteniamo la persona dalla tessera
            Persona persona = tessera.getPersona();

            // Troviamo l'ultima timbratura per questa persona
            TimbraturaDipendente ultimaTimbratura = null;
            List<TimbraturaDipendente> timbrature = timbraturaDipendenteRepository.listAll().stream()
                .filter(t -> t.getTessera().getPersona().getIdPersona().equals(persona.getIdPersona()))
                .sorted((t1, t2) -> t2.getDataOraTimbratura().compareTo(t1.getDataOraTimbratura()))
                .collect(Collectors.toList());

            if (!timbrature.isEmpty()) {
                ultimaTimbratura = timbrature.get(0);
            }

            if (ultimaTimbratura == null || "USCITA".equals(ultimaTimbratura.getTipoTimbratura())) {
                dto.setTipoTimbratura("ENTRATA");
            } else {
                dto.setTipoTimbratura("USCITA");
            }
        }

        // Crea e salva la nuova timbratura
        TimbraturaDipendente timbratura = new TimbraturaDipendente();
        timbratura.setTessera(tessera);
        timbratura.setDataOraTimbratura(dto.getDataOraTimbratura() != null ?
                dto.getDataOraTimbratura() : LocalDateTime.now());
        timbratura.setTipoTimbratura(dto.getTipoTimbratura());
        timbratura.setNote(dto.getNote());
        timbratura.setValidata(false); // Le nuove timbrature non sono validate di default

        timbraturaDipendenteRepository.persist(timbratura);
        return mapToDTO(timbratura);
    }

    @Transactional
    public TimbraturaDipendenteDTO validaTimbratura(Long id, Long validatorId) {
        TimbraturaDipendente timbratura = timbraturaDipendenteRepository.findById(id);
        if (timbratura == null) {
            throw new NotFoundException("Timbratura non trovata con ID: " + id);
        }

        // Verifica che il validatore esista
        Persona validatore = personaRepository.findById(validatorId);
        if (validatore == null) {
            throw new NotFoundException("Validatore non trovato con ID: " + validatorId);
        }

        timbratura.setValidata(true);
        timbratura.setValidataDa(validatore.getIdPersona());
        timbratura.setDataValidazione(LocalDateTime.now());

        timbraturaDipendenteRepository.persist(timbratura);
        return mapToDTO(timbratura);
    }

    public List<TimbraturaDipendenteDTO> getTimbratureByPersona(Long idPersona) {
        Persona persona = personaRepository.findById(idPersona);
        if (persona == null) {
            throw new NotFoundException("Persona non trovata con ID: " + idPersona);
        }

        // Ora dobbiamo trovare tutte le timbrature relative alle tessere di questa persona
        List<TimbraturaDipendente> timbrature = timbraturaDipendenteRepository.listAll().stream()
            .filter(t -> t.getTessera().getPersona().getIdPersona().equals(persona.getIdPersona()))
            .collect(Collectors.toList());

        return timbrature.stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    public List<TimbraturaDipendenteDTO> getTimbratureByDate(LocalDate data) {
        LocalDateTime inizio = data.atStartOfDay();
        LocalDateTime fine = inizio.plusDays(1).minusNanos(1);

        return timbraturaDipendenteRepository.findTimbraturaByDate(inizio).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<TimbraturaDipendenteDTO> getTimbratureNonValidate() {
        return timbraturaDipendenteRepository.findNonValidate().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteTimbratura(Long id) {
        TimbraturaDipendente timbratura = timbraturaDipendenteRepository.findById(id);
        if (timbratura == null) {
            throw new NotFoundException("Timbratura non trovata con ID: " + id);
        }

        timbraturaDipendenteRepository.delete(timbratura);
    }

    /**
     * Recupera le timbrature di oggi per una persona specifica
     * @param idPersona ID della persona di cui recuperare le timbrature
     * @return Lista delle timbrature odierne della persona
     */
    public List<TimbraturaDipendenteDTO> getTimbratureOdierneByPersona(Long idPersona) {
        // Verifica che la persona esista
        Persona persona = personaRepository.findById(idPersona);
        if (persona == null) {
            throw new NotFoundException("Persona non trovata con ID: " + idPersona);
        }

        // Ottieni la data di oggi
        LocalDate oggi = LocalDate.now();
        LocalDateTime inizioGiorno = oggi.atStartOfDay();
        LocalDateTime fineGiorno = oggi.plusDays(1).atStartOfDay().minusNanos(1);

        // Recupera tutte le timbrature e filtra per persona e data odierna
        List<TimbraturaDipendente> timbrature = timbraturaDipendenteRepository.listAll().stream()
            .filter(t -> t.getTessera().getPersona().getIdPersona().equals(persona.getIdPersona()) &&
                   t.getDataOraTimbratura().isAfter(inizioGiorno) &&
                   t.getDataOraTimbratura().isBefore(fineGiorno))
            .sorted((t1, t2) -> t1.getDataOraTimbratura().compareTo(t2.getDataOraTimbratura()))
            .collect(Collectors.toList());

        return timbrature.stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    private TimbraturaDipendenteDTO mapToDTO(TimbraturaDipendente entity) {
        TimbraturaDipendenteDTO dto = new TimbraturaDipendenteDTO();
        dto.setIdTimbratura(entity.getIdTimbratura() != null ? entity.getIdTimbratura().longValue() : null);
        dto.setIdTessera(entity.getTessera().getIdTessera() != null ? entity.getTessera().getIdTessera().longValue() : null);

        // Otteniamo la persona dalla tessera
        Persona persona = entity.getTessera().getPersona();
        dto.setIdPersona(persona != null && persona.getIdPersona() != null ? persona.getIdPersona().longValue() : null);

        dto.setDataOraTimbratura(entity.getDataOraTimbratura());
        dto.setTipoTimbratura(entity.getTipoTimbratura());
        dto.setNote(entity.getNote());
        dto.setValidata(entity.getValidata());
        dto.setValidataDa(entity.getValidataDa() != null ? entity.getValidataDa().longValue() : null);
        dto.setDataValidazione(entity.getDataValidazione());

        // Aggiunta di informazioni aggiuntive per risposta
        if (persona != null) {
            dto.setNomeDipendente(persona.getNome());
            dto.setCognomeDipendente(persona.getCognome());
        }
        dto.setCodiceTessera(entity.getTessera().getCodiceTessera());

        return dto;
    }
}
