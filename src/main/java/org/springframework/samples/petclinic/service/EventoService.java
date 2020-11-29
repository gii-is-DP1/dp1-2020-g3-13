package org.springframework.samples.petclinic.service;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    @Transactional
	public void save(Evento evento) throws DataAccessException {

        eventoRepository.save(evento);
    }
    
    public void delete(Evento evento){
        eventoRepository.delete(evento);
    }

    public Evento findEventoById(int eventoId) {
        return eventoRepository.findById(eventoId).get();
    }

    
    
}
