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

    public Iterable<LineaFactura> findAll(){
        return lineaFacturaRepository.findAll();
    }

    public void save(LineaFactura lineaFactura){
         lineaFacturaRepository.save(lineaFactura);

    }
}