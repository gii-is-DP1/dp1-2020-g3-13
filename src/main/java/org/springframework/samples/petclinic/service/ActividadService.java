package org.springframework.samples.petclinic.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.repository.ActividadRepository;
import org.springframework.stereotype.Service;

@Service
public class ActividadService {

        @Autowired
        private ActividadRepository actividadRepo;

        @Transactional
        public int actividadesCount(){
            return (int) actividadRepo.count();
        }

        public Iterable<Actividad> findAll(){
            return actividadRepo.findAll();
        }

}
