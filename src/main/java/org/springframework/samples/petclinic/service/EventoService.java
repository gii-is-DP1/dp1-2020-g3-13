package org.springframework.samples.petclinic.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.repository.EventoRepository;
import org.springframework.stereotype.Service;

@Service
public class EventoService {
    

    @Autowired
    private EventoRepository eventoRepository;

    @Transactional
    public int eventosCount(){
        return (int) eventoRepository.count();
    }

    public Iterable<Evento> findAll(){
        return eventoRepository.findAll();
    }
}