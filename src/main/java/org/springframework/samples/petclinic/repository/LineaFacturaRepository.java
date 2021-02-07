package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.LineaFactura;

public interface LineaFacturaRepository extends CrudRepository<LineaFactura, Integer>{
    
    @Query("SELECT lineaFactura FROM LineaFactura lineaFactura WHERE lineaFactura.alquilerEspacio.id=:id_alquiler")
    public LineaFactura encuentraAlquiler(int id_alquiler);

    
    @Query("SELECT lineaFactura FROM LineaFactura lineaFactura WHERE lineaFactura.factura.id=:facturaId")
    public List<LineaFactura> lineaFacturaDeFactura(int facturaId);
    @Modifying
    @Query("DELETE FROM LineaFactura lineaFactura WHERE lineaFactura.carrito.id IN(SELECT carrito.id FROM Carrito carrito WHERE carrito.organizacion.id=:organizacionId)")
    public void eliminaLineaFacturaDeOrganizacion(int organizacionId);

}
