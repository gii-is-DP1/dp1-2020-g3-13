package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Carrito;

public interface CarritoRepository extends CrudRepository<Carrito, Integer> {
    
}
