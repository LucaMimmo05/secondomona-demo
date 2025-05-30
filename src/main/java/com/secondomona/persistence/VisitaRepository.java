package com.secondomona.persistence;


import com.secondomona.persistence.model.RichiestaVisita;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class VisitaRepository implements PanacheRepositoryBase<RichiestaVisita, Long> {

    public List<RichiestaVisita> findVisiteAttive() {
        OffsetDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Rome")).toOffsetDateTime();
        return getEntityManager().createQuery("SELECT r FROM RichiestaVisita r WHERE r.dataInizio <= :now AND r.dataFine >= :now", RichiestaVisita.class).setParameter("now", now).getResultList();
    }

    public List<RichiestaVisita> findVisiteInAttesa() {
        OffsetDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Rome")).toOffsetDateTime();
        OffsetDateTime endOfDay = ZonedDateTime.now(ZoneId.of("Europe/Rome"))
                .toLocalDate()
                .atTime(23, 59, 59)
                .atZone(ZoneId.of("Europe/Rome"))
                .toOffsetDateTime();

        return getEntityManager().createQuery(
                        "SELECT r FROM RichiestaVisita r WHERE r.dataInizio BETWEEN :now AND :endOfDay",
                        RichiestaVisita.class)
                .setParameter("now", now)
                .setParameter("endOfDay", endOfDay)
                .getResultList();
    }

    @Transactional
    public RichiestaVisita createVisita(RichiestaVisita visita) {
        System.out.println(visita.getRichiedente().getAntincendio());
        persist(visita);
        return visita;
    }

    public Optional<RichiestaVisita> concludiVisita(Integer idRichiesta) {
        RichiestaVisita visita = getEntityManager()
                .createQuery("SELECT r FROM RichiestaVisita r WHERE r.idRichiesta = :id", RichiestaVisita.class)
                .setParameter("id", idRichiesta)
                .getResultStream()
                .findFirst()
                .orElse(null);

        if (visita != null) {
            // Impostiamo la data di fine alla data e ora attuale
            OffsetDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Rome")).toOffsetDateTime();
            visita.setDataFine(now);
            getEntityManager().merge(visita);
            return Optional.of(visita);
        }

        return Optional.empty();
    }
}
