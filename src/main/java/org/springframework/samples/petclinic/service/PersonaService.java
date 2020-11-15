package org.springframework.samples.petclinic.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.repository.PersonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepo;
    @Transactional
    public int personaCount(){
    return (int) personaRepo.count();
    }
    
}
