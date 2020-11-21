package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Autoridades;

public interface AutoridadesRepository extends CrudRepository<Autoridades, String> {
    
}
