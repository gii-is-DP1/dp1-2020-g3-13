package org.springframework.samples.petclinic.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.repository.EntradaRepository;
import org.springframework.samples.petclinic.repository.EventoRepository;
import org.springframework.samples.petclinic.repository.TipoEntradaRepository;
import org.springframework.stereotype.Service;

@Service
public class EntradaService {
    @Autowired
    private EntradaRepository entradaRepo;
    @Autowired
    private TipoEntradaRepository tipoEntradaRepo;



    @Transactional
    public void crearEntrada(Entrada entrada, Integer tipoEntradaId) throws DataAccessException{
        TipoEntrada tipoEntrada = tipoEntradaRepo.findById(tipoEntradaId).get();
        entrada.setTipoEntrada(tipoEntrada);
        entradaRepo.save(entrada);
    }
    
}
