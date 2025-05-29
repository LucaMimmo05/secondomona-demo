package com.secondomona.persistence;


import com.secondomona.persistence.model.RichiestaVisita;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

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


    public RichiestaVisita createVisita(RichiestaVisita visita) {
        System.out.println(visita.getRichiedente().getAntincendio());
        persist(visita);
        return visita;
    }
}
