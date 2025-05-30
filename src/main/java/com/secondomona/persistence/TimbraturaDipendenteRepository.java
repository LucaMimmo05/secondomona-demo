package com.secondomona.persistence;

import com.secondomona.persistence.model.Persona;
import com.secondomona.persistence.model.TimbraturaDipendente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class TimbraturaDipendenteRepository implements PanacheRepository<TimbraturaDipendente> {

    public List<TimbraturaDipendente> findByPersona(Persona persona) {
        return list("persona", persona);
    }

    public List<TimbraturaDipendente> findByPersonaAndDateRange(Persona persona, OffsetDateTime dataInizio, OffsetDateTime dataFine) {
        return list("persona = ?1 AND dataOraTimbratura >= ?2 AND dataOraTimbratura <= ?3",
                  persona, dataInizio, dataFine);
    }

    public TimbraturaDipendente findLastTimbratura(Persona persona) {
        return find("persona = ?1 ORDER BY dataOraTimbratura DESC", persona)
               .firstResult();
    }

    public List<TimbraturaDipendente> findTimbraturaByDate(OffsetDateTime data) {
        OffsetDateTime inizio = data.toLocalDate().atStartOfDay().atOffset(data.getOffset());
        OffsetDateTime fine = inizio.plusDays(1).minusNanos(1);
        return list("dataOraTimbratura >= ?1 AND dataOraTimbratura <= ?2", inizio, fine);
    }

    public List<TimbraturaDipendente> findNonValidate() {
        return list("validata = false");
    }

    /**
     * Trova i dipendenti attualmente presenti in azienda.
     * Un dipendente è considerato presente se ha una timbratura di ENTRATA
     * senza una successiva timbratura di USCITA nella stessa giornata.
     *
     * @return Lista di timbrature di entrata dei dipendenti attualmente presenti
     */
    public List<TimbraturaDipendente> findDipendentiPresenti() {
        // Trova l'inizio della giornata corrente
        OffsetDateTime inizioGiorno = OffsetDateTime.now().toLocalDate().atStartOfDay()
                .atOffset(OffsetDateTime.now().getOffset());

        // Query personalizzata per trovare le ultime timbrature per ogni persona nella giornata corrente
        return getEntityManager().createQuery(
                "SELECT t1 FROM TimbraturaDipendente t1 " +
                "WHERE t1.dataOraTimbratura >= :inizioGiorno " +
                "AND t1.tipoTimbratura = 'ENTRATA' " +
                "AND NOT EXISTS (" +
                "    SELECT t2 FROM TimbraturaDipendente t2 " +
                "    WHERE t2.tessera.persona = t1.tessera.persona " +
                "    AND t2.dataOraTimbratura > t1.dataOraTimbratura " +
                "    AND t2.dataOraTimbratura >= :inizioGiorno " +
                "    AND t2.tipoTimbratura = 'USCITA'" +
                ")", TimbraturaDipendente.class)
                .setParameter("inizioGiorno", inizioGiorno)
                .getResultList();
    }
}
