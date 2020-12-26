package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Organizacion;

public interface EventoRepository extends CrudRepository<Evento, Integer>{
    

    @Query("SELECT evento FROM Evento evento WHERE evento.organizacion.id=:id_organizacion")
    public List<Evento> listadoEventosDeOrganizacion(int id_organizacion);

}
