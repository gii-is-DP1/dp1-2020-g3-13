package org.springframework.samples.petclinic.repository;
import org.springframework.samples.petclinic.model.Persona;
import org.springframework.data.repository.CrudRepository;
public interface PersonaRepository extends CrudRepository<Persona,Integer>{
    
}
