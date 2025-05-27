package com.secondomona.persistence;

import com.secondomona.persistence.model.Persona;
import com.secondomona.persistence.model.Tessera;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TesseraRepository implements PanacheRepository<Tessera> {

    public Tessera findByCodiceTessera(String codiceTessera) {
        return find("codiceTessera", codiceTessera).firstResult();
    }

    public Tessera findByCodiceEsterno(String codiceEsterno) {
        return find("codiceEsterno", codiceEsterno).firstResult();
    }
}
