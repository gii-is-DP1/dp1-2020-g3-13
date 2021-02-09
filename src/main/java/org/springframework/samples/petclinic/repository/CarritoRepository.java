package org.springframework.samples.petclinic.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Carrito;
import org.springframework.samples.petclinic.model.LineaFactura;

public interface CarritoRepository extends CrudRepository<Carrito, Integer> {
    
    
    @Query("SELECT carrito FROM Carrito carrito WHERE carrito.cliente.usuario.nombreUsuario =:usuario")
    public Carrito dimeCarritoDeUsuario(String usuario);
    @Query("SELECT carrito FROM Carrito carrito WHERE carrito.organizacion.usuario.nombreUsuario =:usuario")
    public Carrito dimeCarritoDeUsuarioOrganizacion(String usuario);
    @Query("SELECT lineaFactura FROM LineaFactura lineaFactura WHERE lineaFactura.carrito.id=:id_carrito")
    public List<LineaFactura> dimeLineaFacturaCarrito(int id_carrito);
    @Modifying
    @Query("DELETE FROM Carrito carrito WHERE carrito.organizacion.id=:organizacionId")
    public void eliminaCarritoOrganizacion(int organizacionId);
    


}
