package com.secondomona.persistence;

import com.secondomona.persistence.model.Persona;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

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
}
