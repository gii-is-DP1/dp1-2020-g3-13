package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Carrito;
import org.springframework.samples.petclinic.model.LineaFactura;

public interface CarritoRepository extends CrudRepository<Carrito, Integer> {
    
    
    @Query("SELECT carrito FROM Carrito carrito WHERE carrito.cliente.usuario.nombreUsuario =:usuario")
    public Iterable<Carrito> dimeCarritoDeUsuario(String usuario);


}
