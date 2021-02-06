package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.AlquilerEspacio;
import org.springframework.samples.petclinic.model.LugarRealizacion;

public interface LugarRealizacionRepository extends CrudRepository<LugarRealizacion,Integer> {
    public LugarRealizacion findById(int id);
    public Iterable<LugarRealizacion> findAll() throws DataAccessException;

    @Query("SELECT alquilerEspacio FROM AlquilerEspacio alquilerEspacio WHERE alquilerEspacio.lugarRealizacion.id =:lugarId")
    public List<AlquilerEspacio> alquilerEspacioDeLugar(int lugarId);

}
