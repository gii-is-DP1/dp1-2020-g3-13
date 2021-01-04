package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.LineaFactura;
import org.springframework.samples.petclinic.repository.FacturaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepo;

    @Transactional
    public int facturaCount(){
    return (int) facturaRepo.count();
    }
    public Iterable<Factura> findAll(){
        return facturaRepo.findAll();
    }
    public void save(Factura Factura){
        facturaRepo.save(Factura);

   }
   public void calculaPrecioTotal(List<Factura> facturas){
        int i = 0;
        Double precio = 0.;
        while (i<facturas.size()){
            int j = 0;
            
            List<LineaFactura> lineas = facturas.get(i).getLineasFacturas();
            if(! (lineas==null)){
            while (j<lineas.size()){
                precio += lineas.get(j).getAlquilerEspacio().getPrecio();
                j++;
            }
        }
            facturas.get(i).setPrecioTotal(precio);
            save(facturas.get(i));
            i++;
        }

   }
    
}


