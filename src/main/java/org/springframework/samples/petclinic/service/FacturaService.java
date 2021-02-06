package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.LineaFactura;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.repository.FacturaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepo;
    @Autowired
    private LineaFacturaService lineaService;

    @Transactional
    public int facturaCount(){
    return (int) facturaRepo.count();
    }
    public Iterable<Factura> findAll(){
        return facturaRepo.findAll();
    }
    @Transactional
    public void save(Factura Factura){
        facturaRepo.save(Factura);

   }

   public List<Factura> facturasUsuario(Usuario usuario){
       return facturaRepo.facturasDeUsuario(usuario);
   }
   public List<LineaFactura> lineasFacturaDeFactura(int facturaId){
       return facturaRepo.lineasFacturaDeFactura(facturaId);
   }


//    public void calculaPrecioTotal(List<Factura> facturas){
//         int i = 0;
//         Double precio = 0.;
//         while (i<facturas.size()){
//             int j = 0;
            
//             List<LineaFactura> lineas = lineaService.lineasFacturaDeFactura(facturas.get(i).getId());
//             if(! (lineas==null)){
//             while (j<lineas.size()){
//                 precio += lineas.get(j).getAlquilerEspacio().getPrecioTotal();
//                 j++;
//             }
//         }
//             facturas.get(i).setPrecioTotal(precio);
//             save(facturas.get(i));
//             i++;
//         }

//    }
    
}


