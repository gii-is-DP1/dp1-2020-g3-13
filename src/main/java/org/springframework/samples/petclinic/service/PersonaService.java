package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.repository.PersonaRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepo;

    public int personaCount() {
        return (int) personaRepo.count();
    }

}
