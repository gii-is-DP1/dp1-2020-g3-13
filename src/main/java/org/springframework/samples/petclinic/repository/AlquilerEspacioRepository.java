package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.AlquilerEspacio;

public interface AlquilerEspacioRepository extends CrudRepository<AlquilerEspacio, String>{
    
}
