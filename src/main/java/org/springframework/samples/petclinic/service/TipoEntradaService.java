package org.springframework.samples.petclinic.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.repository.TipoEntradaRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoEntradaService {
    

    @Autowired
    private TipoEntradaRepository tipoEntradaRepository;

    @Transactional
    public int tipoEntradaCount(){
        return (int) tipoEntradaRepository.count();
    }

    public Iterable<TipoEntrada> findAll(){
        return tipoEntradaRepository.findAll();
    }

    public void anadirTipoEntrada(Evento evento, TipoEntrada tipoEntrada){
        tipoEntrada.setEvento(evento);
    }

    public List<Entrada> EncontrarTodasLasEntradas(TipoEntrada tipoEntrada){
        return tipoEntradaRepository.findAllEntradas(tipoEntrada.getId());
    }

    public void guardar(TipoEntrada tipoEntrada){
        tipoEntradaRepository.save(tipoEntrada);
    }

}