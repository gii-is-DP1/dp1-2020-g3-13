package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Exponente;
import org.springframework.samples.petclinic.repository.ActividadRepository;
import org.springframework.samples.petclinic.repository.ExponenteRepository;
import org.springframework.stereotype.Service;

@Service
public class ExponenteService {


    @Autowired
    private ExponenteRepository exponenteRepo;
    @Autowired
    private ActividadService actividadService;


    @Transactional
    public void guardarExponente(Exponente exponente) throws DataAccessException{
        exponenteRepo.save(exponente);
    }

    @Transactional
    public Exponente encuentraExponente(Exponente exponente){
        String alias = exponente.getAlias();
        String apellidos = exponente.getApellidosExponente();
        String nombre = exponente.getNombreExponente();
        if(exponenteRepo.existeExponenteConEsteAlias(alias) != null 
        && exponenteRepo.existeExponenteConEsteApellidos(apellidos) != null 
        && exponenteRepo.existeExponenteConEsteNombre(nombre) != null){

            return exponente;
            }else{
                return null;
            }
        
        }

    @Transactional
    public void anadirExponente(Actividad actividad, Exponente exponente) throws DataAccessException{
        if(exponenteRepo.existeExponenteConEsteNombre(exponente.getNombreExponente()) == null 
        && exponenteRepo.existeExponenteConEsteApellidos(exponente.getApellidosExponente()) == null
        && exponenteRepo.existeExponenteConEsteAlias(exponente.getAlias()) == null){
            exponenteRepo.save(exponente);
            List<Actividad> actividades = new ArrayList<>();
            actividades.add(actividad);
            exponente.setActividades(actividades);

        }else{
            if(encuentraExponente(exponente)!=null){
                List<Actividad> actividadesActual =exponente.getActividades();
                actividadesActual.add(actividad);
                exponente.setActividades(actividadesActual);
            }
        }
    }


    }