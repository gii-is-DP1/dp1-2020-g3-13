package org.springframework.samples.petclinic.service;

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
}