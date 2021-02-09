package org.springframework.samples.petclinic.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.LineaFactura;
import org.springframework.samples.petclinic.repository.LineaFacturaRepository;
import org.springframework.stereotype.Service;

@Service
public class LineaFacturaService {
    

    @Autowired
    private LineaFacturaRepository lineaFacturaRepository;

    @Transactional
    public int lineaFacturaCount(){
        return (int) lineaFacturaRepository.count();
    }

    @Transactional
    public LineaFactura encuentraLineaFactura(int lineaFacturaId){
        return lineaFacturaRepository.findById(lineaFacturaId).orElse(null);
    }

    @Transactional
    public Iterable<LineaFactura> findAll(){
        return lineaFacturaRepository.findAll();
    }
    @Transactional
    public void save(LineaFactura lineaFactura){
         lineaFacturaRepository.save(lineaFactura);

    }

    @Transactional
    public LineaFactura encuentraAlquilerEspacio(int alqId){
        return lineaFacturaRepository.encuentraAlquiler(alqId);
    }

    @Transactional
    public void borrarLinea(LineaFactura linea){
        lineaFacturaRepository.delete(linea);
    }

    public List<LineaFactura> lineasFacturaDeFactura(int facturaId){
        return lineaFacturaRepository.lineaFacturaDeFactura(facturaId);
    }

    @Transactional
    public void eliminaLineaFacturaDeOrganizacion(int organizacionId){
        lineaFacturaRepository.eliminaLineaFacturaDeOrganizacion(organizacionId);
    }
}