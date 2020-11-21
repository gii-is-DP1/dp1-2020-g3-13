package org.springframework.samples.petclinic.service;

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

    @Transactional
    public int peticionCount(){
    return (int) peticionRepo.count();
    }
    @Transactional
    public Iterable<Peticion> dimeTodas(){
        return peticionRepo.findAll();
    }


            
    @Transactional
    public void saveOrganizacion(Peticion organizacion) throws DataAccessException{
        peticionRepo.save(organizacion);
    }

    @Transactional
    public Peticion findById(int organizacionId) throws DataAccessException{
        return peticionRepo.findById(organizacionId);
    }

public void deleteOrganizacion(int organizacionId) {
    peticionRepo.delete(peticionRepo.findById(organizacionId));
}

}









