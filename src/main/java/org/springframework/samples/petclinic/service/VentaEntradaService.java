package org.springframework.samples.petclinic.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.VentaEntrada;
import org.springframework.samples.petclinic.repository.VentaEntradaRepository;
import org.springframework.stereotype.Service;

@Service
public class VentaEntradaService {
    
    @Autowired
    private VentaEntradaRepository ventaEntradaRepository;

    @Transactional
    public void saveEntrada(VentaEntrada ventaEntrada) throws DataAccessException{
        ventaEntradaRepository.save(ventaEntrada);
    }
}
