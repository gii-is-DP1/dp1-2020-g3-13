package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.TipoEntrada;


public interface TipoEntradaRepository extends CrudRepository<TipoEntrada, Integer> {

    @Query("SELECT entrada FROM Entrada entrada WHERE entrada.tipoEntrada.id=:id_tipo_entrada")
    public List<Entrada> findAllEntradas(Integer id_tipo_entrada);

    
    
}
