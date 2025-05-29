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
        Integer id = persona.getIdPersona();

        // Cerco una tessera Visitatore senza assegnazioni attive
        Tessera tesseraDaAssegnare = tesseraRepository.find(
                "FROM Tessera t WHERE t.categorie = 'Visitatore' AND NOT EXISTS (" +
                        "SELECT 1 FROM AssegnazioneBadge ab WHERE ab.tessera = t AND ab.dataFine IS NULL)"
        ).firstResult();

        tesseraDaAssegnare.setPersona(persona);

        if (tesseraDaAssegnare == null) {
            throw new IllegalStateException("Nessun badge disponibile per l'assegnazione alla persona con ID: " + id);
        }

        AssegnazioneBadge nuovaAssegnazione = new AssegnazioneBadge();
        nuovaAssegnazione.setTessera(tesseraDaAssegnare);
        nuovaAssegnazione.setPersona(persona);
        nuovaAssegnazione.setDataInizio(LocalDateTime.now());
        nuovaAssegnazione.setDataFine(null);

        persist(nuovaAssegnazione);
        flush();

        return nuovaAssegnazione;
    }

}
