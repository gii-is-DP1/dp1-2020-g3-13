package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.AlquilerEspacio;
public interface ActividadRepository extends CrudRepository<Actividad, Integer>{
    
    @Query("SELECT alquilerEspacio FROM Actividad actividad WHERE actividad.alquilerEspacio.id =:alquilerId")
    public AlquilerEspacio encuentraAlquilerLugar(int alquilerId);
}
