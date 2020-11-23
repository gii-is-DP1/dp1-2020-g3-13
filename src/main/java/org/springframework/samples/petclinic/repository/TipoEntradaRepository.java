package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.TipoEntrada;


public interface TipoEntradaRepository extends CrudRepository<TipoEntrada, Integer> {
    
}
