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
    public int cuentaFactura(){
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
public void eliminaFacturaDeUsuario(String nombreUsuario) {
    facturaRepo.eliminaFacturaDeUsuario(nombreUsuario);
}
    
}


