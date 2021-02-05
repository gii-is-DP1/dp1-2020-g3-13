package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.AlquilerEspacio;

public interface AlquilerEspacioRepository extends CrudRepository<AlquilerEspacio, Integer>{
    
    @Query("SELECT actividad FROM Actividad actividad WHERE actividad.alquilerEspacio.id =:alquilerId")
    public Actividad encuentraActividadPorAlquilerId(int alquilerId);
}
