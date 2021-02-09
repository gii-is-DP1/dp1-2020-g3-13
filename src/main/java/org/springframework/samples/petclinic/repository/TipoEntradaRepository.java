package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.TipoEntrada;


public interface TipoEntradaRepository extends CrudRepository<TipoEntrada, Integer> {

    @Query("SELECT entrada FROM Entrada entrada WHERE entrada.tipoEntrada.id=:id_tipo_entrada")
    public List<Entrada> findAllEntradas(Integer id_tipo_entrada);

    @Query("SELECT tipoEntrada FROM TipoEntrada tipoEntrada WHERE tipoEntrada.evento.id=:eventoId")
    public List<TipoEntrada> encuentraTodasLasEntradasDeEvento(int eventoId);

    
    @Modifying
    @Query("DELETE FROM TipoEntrada tipoEntrada WHERE tipoEntrada.evento.id IN(SELECT evento.id FROM Evento evento WHERE evento.organizacion.id=:organizacionId)")
    public void eliminaTipoEntradaDeOrganizacion(int organizacionId);

    @Modifying
    @Query("DELETE FROM TipoEntrada tipoEntrada WHERE tipoEntrada.evento.id=:eventoId")
    public void eliminaTipoEntradaDeEvento(int eventoId);

    
}
