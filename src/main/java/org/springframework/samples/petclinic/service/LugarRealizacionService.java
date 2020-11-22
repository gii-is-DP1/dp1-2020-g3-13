package org.springframework.samples.petclinic.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.LugarRealizacion;
import org.springframework.samples.petclinic.repository.LugarRealizacionRepository;
import org.springframework.stereotype.Service;

@Service
public class LugarRealizacionService {
    private LugarRealizacionRepository lugarRealizacionRepository;

    @Autowired
    public LugarRealizacionService(LugarRealizacionRepository lugarRealizacionRepository){
        this.lugarRealizacionRepository = lugarRealizacionRepository;
    }
    @Transactional
    public void saveLugarRealizacion(LugarRealizacion lugarRealizacion){
        lugarRealizacionRepository.save(lugarRealizacion);
    }

    public Iterable<LugarRealizacion> findAll(){
        return lugarRealizacionRepository.findAll();
    }

    @Transactional
	public LugarRealizacion findById(int lugarId) throws DataAccessException {
		return lugarRealizacionRepository.findById(lugarId);
    }

}
