package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.repository.OrganizacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizacionService {

        @Autowired
        private OrganizacionRepository organizacionRepo;

        @Transactional
        public int organizacionCount(){
            return (int) organizacionRepo.count();
        }

        public Iterable<Organizacion> findAll(){
            return organizacionRepo.findAll();
        }

    }
    
