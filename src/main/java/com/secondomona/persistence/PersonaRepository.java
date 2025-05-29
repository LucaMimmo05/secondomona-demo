package com.secondomona.persistence;

import com.secondomona.persistence.model.Persona;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PersonaRepository implements PanacheRepositoryBase<Persona, Long> {

    public Persona authenticate(String email, String password) {
        Persona persona = findByEmail(email);
        if (persona == null) {
            return null;
        }
        boolean matches = BcryptUtil.matches(password, persona.getPasswordHash());
        if (matches) {
            return persona;
        }
        return null;
    }

    public Persona findByEmail(String email) {
        return find(
                "SELECT p FROM Persona p WHERE " +
                        "p.mail = :mail",
                Parameters.with("mail", email)
        ).firstResult();
    }

    public Persona findByNumeroDocumento(String numeroDocumento) {
        return find(
                "SELECT p FROM Persona p WHERE " +
                        "p.numeroDocumento = :numeroDocumento",
                Parameters.with("numeroDocumento", numeroDocumento)
        ).firstResult();
    }

    public List<Persona> getDipendenti() {
        return getEntityManager()
                .createQuery("SELECT r FROM Persona r WHERE r.visitatore = :isDipendente", Persona.class)
                .setParameter("isDipendente", false)
                .getResultList();
    }

    public List<Persona> getVisitatori() {
        return getEntityManager()
                .createQuery("SELECT r FROM Persona r WHERE r.visitatore = :isVisitatore", Persona.class)
                .setParameter("isVisitatore", true)
                .getResultList();
    }

    @Transactional
    public Persona save(Persona persona) {
        persistAndFlush(persona);
        return persona;
    }
}
