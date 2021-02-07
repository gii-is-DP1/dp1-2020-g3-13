package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Peticion;
import org.springframework.samples.petclinic.repository.PeticionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeticionService {
    @Autowired
    private PeticionRepository peticionRepo;

    public int peticionCount() {
        return (int) peticionRepo.count();
    }

    public Iterable<Peticion> dimeTodas() {
        return peticionRepo.findAll();
    }

    public Optional<Peticion> findPeticionById(int peticionId) {
        return peticionRepo.findById(peticionId);
    }

    @Transactional
    public void savePeticion(Peticion organizacion) throws DataAccessException {
        peticionRepo.save(organizacion);
    }

    @Transactional
    public void deletePeticion(Peticion peticion) {
        peticionRepo.delete(peticion);
    }

    @Transactional
    public void createPeticion(Peticion peticion) {
        peticion.setFecha(LocalDate.now());
        savePeticion(peticion);
    }

}
