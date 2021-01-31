package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.LineaFactura;

public interface LineaFacturaRepository extends CrudRepository<LineaFactura, Integer>{
    
    @Query("SELECT lineaFactura FROM LineaFactura lineaFactura WHERE lineaFactura.alquilerEspacio.id=:id_alquiler")
    public LineaFactura encuentraAlquiler(int id_alquiler);
}
