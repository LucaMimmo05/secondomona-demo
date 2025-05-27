package com.secondomona.persistence;

import com.secondomona.persistence.model.Persona;
import com.secondomona.persistence.model.TimbraturaDipendente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class TimbraturaDipendenteRepository implements PanacheRepository<TimbraturaDipendente> {

    public List<TimbraturaDipendente> findByPersona(Persona persona) {
        return list("persona", persona);
    }

    public List<TimbraturaDipendente> findByPersonaAndDateRange(Persona persona, LocalDateTime dataInizio, LocalDateTime dataFine) {
        return list("persona = ?1 AND dataOraTimbratura >= ?2 AND dataOraTimbratura <= ?3",
                  persona, dataInizio, dataFine);
    }

    public TimbraturaDipendente findLastTimbratura(Persona persona) {
        return find("persona = ?1 ORDER BY dataOraTimbratura DESC", persona)
               .firstResult();
    }

    public List<TimbraturaDipendente> findTimbraturaByDate(LocalDateTime data) {
        LocalDateTime inizio = data.toLocalDate().atStartOfDay();
        LocalDateTime fine = inizio.plusDays(1).minusNanos(1);
        return list("dataOraTimbratura >= ?1 AND dataOraTimbratura <= ?2", inizio, fine);
    }

    public List<TimbraturaDipendente> findNonValidate() {
        return list("validata = false");
    }
}
