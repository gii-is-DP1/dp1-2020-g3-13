package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Consulta;

public interface ConsultaRepository extends CrudRepository<Consulta, Integer>{
    
    @Modifying
    @Query("DELETE FROM Consulta consulta WHERE consulta.evento.id IN(SELECT evento.id FROM Evento evento WHERE evento.organizacion.id=:organizacionId)")
    public void eliminaConsultasDeOrganizacion(int organizacionId);
}
