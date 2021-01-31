package org.springframework.samples.petclinic.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Carrito;

public interface CarritoRepository extends CrudRepository<Carrito, Integer> {
    
    
    @Query("SELECT carrito FROM Carrito carrito WHERE carrito.cliente.usuario.nombreUsuario =:usuario")
    public Carrito dimeCarritoDeUsuario(String usuario);
    @Query("SELECT carrito FROM Carrito carrito WHERE carrito.organizacion.usuario.nombreUsuario =:usuario")
    public Carrito dimeCarritoDeUsuarioOrganizacion(String usuario);


}
