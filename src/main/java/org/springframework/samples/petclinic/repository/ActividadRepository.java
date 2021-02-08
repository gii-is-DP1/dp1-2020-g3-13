package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.AlquilerEspacio;
public interface ActividadRepository extends CrudRepository<Actividad, Integer>{
    
    @Query("SELECT alquilerEspacio FROM Actividad actividad WHERE actividad.alquilerEspacio.id =:alquilerId")
    public AlquilerEspacio encuentraAlquilerLugar(int alquilerId);

    @Query("SELECT actividad FROM Actividad actividad WHERE actividad.alquilerEspacio.id =:alquilerId")
    public Actividad encuentraLugarAlquiler(int alquilerId);

    @Query("SELECT actividad FROM Actividad actividad WHERE actividad.evento.id =:eventoId")
    public List<Actividad> encuentrActividadesEventoId(int eventoId);

    @Query("SELECT actividad FROM Actividad actividad WHERE actividad.evento.organizacion.id=:organizacionId")
    public List<Actividad> encuentraActividadesDeOrganizacion(int organizacionId);

    @Modifying
    @Query("DELETE FROM Actividad actividad WHERE actividad.evento.id IN(SELECT evento.id FROM Evento evento WHERE evento.organizacion.id=:organizacionId)")
    public void eliminaActividadesOrganizacion(int organizacionId);
}
