package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Exponente;

public interface ExponenteRepository extends CrudRepository<Exponente, Integer>{
    
}
