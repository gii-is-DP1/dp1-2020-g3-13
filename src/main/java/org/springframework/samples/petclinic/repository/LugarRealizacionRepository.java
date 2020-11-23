package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.LugarRealizacion;

public interface LugarRealizacionRepository extends CrudRepository<LugarRealizacion,Integer> {
    public LugarRealizacion findById(int id);
}
