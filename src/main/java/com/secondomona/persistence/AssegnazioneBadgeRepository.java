package com.secondomona.persistence;

import com.secondomona.persistence.model.AssegnazioneBadge;
import com.secondomona.persistence.model.Persona;
import com.secondomona.persistence.model.Tessera;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class AssegnazioneBadgeRepository implements PanacheRepository<AssegnazioneBadge> {

    public List<AssegnazioneBadge> findByPersona(Persona persona) {
        return list("persona", persona);
    }

    public List<AssegnazioneBadge> findByTessera(Tessera tessera) {
        return list("tessera", tessera);
    }

    public AssegnazioneBadge findActiveAssegnazione(Persona persona) {
        return find("persona = ?1 AND dataFine IS NULL", persona).firstResult();
    }
}
