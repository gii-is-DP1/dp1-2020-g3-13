package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Entrada;

public interface EntradaRepository extends CrudRepository<Entrada, Integer>{
    
    
    @Query("SELECT entrada FROM Entrada entrada WHERE entrada.nombreAsistente =:nombreAsistente")
    public Entrada findEntradaByNombreAsistente(String nombreAsistente);
    @Query("SELECT entrada FROM Entrada entrada WHERE entrada.cliente.id =:cliente_id")
    public List<Entrada> findEntradaByClienteId(int cliente_id);
}
