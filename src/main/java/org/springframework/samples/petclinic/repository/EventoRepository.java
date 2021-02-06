package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Evento;

public interface EventoRepository extends CrudRepository<Evento, Integer>{
    @Query("SELECT evento FROM Evento evento WHERE evento.organizacion.id=:id_organizacion ORDER BY evento.fechaInicio ASC")
    public List<Evento> listadoEventosDeOrganizacion(int id_organizacion);

    @Query("SELECT evento FROM Evento evento WHERE evento.esPublico=TRUE ORDER BY evento.fechaInicio ASC")
    public List<Evento> listadoEventosOrdenadosPorFecha();
    @Query("SELECT actividad FROM Actividad actividad WHERE actividad.evento.id=:id_evento")
    public List<Actividad> getActividades(int id_evento);
}
