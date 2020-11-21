package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Organizacion;

public interface OrganizacionRepository extends CrudRepository<Organizacion, Integer>{
    
}
