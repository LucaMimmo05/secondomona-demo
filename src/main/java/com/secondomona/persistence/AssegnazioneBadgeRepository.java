package com.secondomona.persistence;

import com.secondomona.persistence.model.AssegnazioneBadge;
import com.secondomona.persistence.model.Persona;
import com.secondomona.persistence.model.Tessera;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class AssegnazioneBadgeRepository implements PanacheRepository<AssegnazioneBadge> {

    @Inject
    TesseraRepository tesseraRepository;

    public List<AssegnazioneBadge> findByPersona(Persona persona) {
        return list("persona", persona);
    }

    public List<AssegnazioneBadge> findByTessera(Tessera tessera) {
        return list("tessera", tessera);
    }

    public AssegnazioneBadge findActiveAssegnazione(Persona persona) {
        return find("persona = ?1 AND dataFine IS NULL", persona).firstResult();
    }

    @Transactional
    public AssegnazioneBadge assegnaBadge(Persona persona) {
        AssegnazioneBadge attiva = findActiveAssegnazione(persona);
        if (attiva != null) {
            throw new IllegalStateException("La persona ha già un badge attivo.");
        }
        Integer id = persona.getIdPersona();
        Tessera tesseraDaAssegnare = tesseraRepository.find(
                "categorie = ?1 AND idTessera NOT IN (" +
                        "SELECT ab.tessera.idTessera FROM AssegnazioneBadge ab WHERE ab.dataFine IS NULL)",
                "Visitatore"
        ).firstResult();
        tesseraDaAssegnare.setPersona(persona);
        AssegnazioneBadge nuovaAssegnazione = new AssegnazioneBadge();
        nuovaAssegnazione.setTessera(tesseraDaAssegnare);
        nuovaAssegnazione.setDataInizio(LocalDateTime.now());
        nuovaAssegnazione.setDataFine(null);
        persist(nuovaAssegnazione);
        flush();
        return nuovaAssegnazione;
    }

}
