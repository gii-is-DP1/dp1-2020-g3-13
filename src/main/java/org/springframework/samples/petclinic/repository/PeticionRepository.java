package org.springframework.samples.petclinic.repository;
import org.springframework.samples.petclinic.model.Peticion;
import org.springframework.data.repository.CrudRepository;
public interface PeticionRepository extends CrudRepository<Peticion, Integer>{
   // public Peticion findById(int organizacionId);
    //@Query("SELECT peticion FROM Peticion peticion WHERE   id=:organizacionid")
	//public Peticion findPeticionByOrganizacionId(int organizacionId);
}
