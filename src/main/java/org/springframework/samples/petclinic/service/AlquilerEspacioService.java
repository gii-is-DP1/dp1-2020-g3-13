package org.springframework.samples.petclinic.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.AlquilerEspacio;
import org.springframework.samples.petclinic.repository.AlquilerEspacioRepository;
import org.springframework.stereotype.Service;

@Service
public class AlquilerEspacioService {
    

    @Autowired
    private AlquilerEspacioRepository alquilerEspacioRepository;

    @Transactional
    public int alquilerEspacioCount(){
        return (int) alquilerEspacioRepository.count();
    }

    public Iterable<AlquilerEspacio> findAll(){
        return alquilerEspacioRepository.findAll();
    }
}