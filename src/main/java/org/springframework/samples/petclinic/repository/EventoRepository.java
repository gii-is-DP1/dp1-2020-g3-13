package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Evento;

public interface EventoRepository extends CrudRepository<Evento, String>{
    
}
