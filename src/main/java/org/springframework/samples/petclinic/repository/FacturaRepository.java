package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.LineaFactura;
import org.springframework.samples.petclinic.model.Usuario;
public interface FacturaRepository extends CrudRepository<Factura,Integer>{
    
    @Query("SELECT factura FROM Factura factura WHERE factura.usuario=:usuario")
    public List<Factura> facturasDeUsuario(Usuario usuario);

    @Query("SELECT lineaFactura FROM LineaFactura lineaFactura WHERE lineaFactura.factura.id=:facturaId")
    public List<LineaFactura> lineasFacturaDeFactura(int facturaId);
    @Modifying
    @Query("DELETE FROM Factura factura WHERE factura.usuario.nombreUsuario=:nombreUsuario")
    public void eliminaFacturaDeUsuario(String nombreUsuario);
}