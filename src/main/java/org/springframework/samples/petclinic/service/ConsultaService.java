package org.springframework.samples.petclinic.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Consulta;
import org.springframework.samples.petclinic.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {
    

    @Autowired
    private ConsultaRepository consultaRepository;

    @Transactional
    public int consultasCount(){
        return (int) consultaRepository.count();
    }

    public Iterable<Consulta> findAll(){
        return consultaRepository.findAll();
    }
}
