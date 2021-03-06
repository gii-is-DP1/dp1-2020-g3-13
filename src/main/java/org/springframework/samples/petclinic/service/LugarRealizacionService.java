package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.AlquilerEspacio;
import org.springframework.samples.petclinic.model.LugarRealizacion;
import org.springframework.samples.petclinic.repository.LugarRealizacionRepository;
import org.springframework.stereotype.Service;

@Service
public class LugarRealizacionService {
    @Autowired
    private LugarRealizacionRepository lugarRealizacionRepository;

    @Transactional
    public int lugaresCount(){
        return (int) lugarRealizacionRepository.count();
    }
    @Transactional
    public void saveLugarRealizacion(LugarRealizacion lugarRealizacion){
        lugarRealizacionRepository.save(lugarRealizacion);
    }
    public void modifyLugaRealizacion(LugarRealizacion lugarRealizacion, LugarRealizacion LugarRealizacionact) throws DataAccessException{
		lugarRealizacion.setId(LugarRealizacionact.getId());
        saveLugarRealizacion(lugarRealizacion);
    }
    @Transactional
    public List<Integer> listaIdLugarRealizacion(){
        List<Integer> lista = new ArrayList<Integer>();
        Iterable<LugarRealizacion> iterable = findAll();
        for (LugarRealizacion i: iterable) {
             lista.add(i.getId());
        }
        return lista;
    }
    @Transactional
    public List<String> listaNombresLugarRealizacion(){
        List<String> lista = new ArrayList<String>();
        Iterable<LugarRealizacion> iterable = findAll();
        for (LugarRealizacion i: iterable) {
             lista.add(i.getNombre_recinto());
        }
        return lista;
    }

    @Transactional
    public Iterable<LugarRealizacion> findAll() throws DataAccessException{
        return lugarRealizacionRepository.findAll();
    }
    @Transactional
    public void borrarLugarRealizacion(LugarRealizacion lugarRealizacion) throws DataAccessException{
        lugarRealizacionRepository.delete(lugarRealizacion);    
    }

    @Transactional
	public LugarRealizacion findById(int lugarId) throws DataAccessException {
		return lugarRealizacionRepository.findById(lugarId);
    }
    @Transactional
    public List<AlquilerEspacio> encuentraAlquileresPorLugarId(int lugarId){
        return lugarRealizacionRepository.alquilerEspacioDeLugar(lugarId);
    }

}
