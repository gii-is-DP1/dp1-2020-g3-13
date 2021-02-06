package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.AlquilerEspacio;
import org.springframework.samples.petclinic.model.Exponente;
public interface ActividadRepository extends CrudRepository<Actividad, Integer>{
    
    @Query("SELECT alquilerEspacio FROM Actividad actividad WHERE actividad.alquilerEspacio.id =:alquilerId")
    public AlquilerEspacio encuentraAlquilerLugar(int alquilerId);

    @Query("SELECT actividad FROM Actividad actividad WHERE actividad.alquilerEspacio.id =:alquilerId")
    public Actividad encuentraLugarAlquiler(int alquilerId);
}
