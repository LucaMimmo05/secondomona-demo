package com.secondomona.persistence;


import com.secondomona.dto.RichiestaVisitaDTO;
import com.secondomona.persistence.model.RichiestaVisita;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.List;
@ApplicationScoped
public class VisitaRepository implements PanacheRepositoryBase<RichiestaVisita, Long> {

    public List<RichiestaVisita> findRichiesteAttive() {
        return find("dataFine > ?1", LocalDateTime.now()).list();
    }
}
