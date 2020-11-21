package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Consulta;

public interface ConsultaRepository extends CrudRepository<Consulta, Integer>{
    
}
