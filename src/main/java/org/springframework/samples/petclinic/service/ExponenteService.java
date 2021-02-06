package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Exponente;
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

    public List<Exponente> encuentraActividadExponente(int actividadId){
        return exponenteRepo.encuentraActividadExponente(actividadId);
    }

    
        public Exponente buscaExponente(Exponente exponente){
            Exponente exponenteABuscar = null;
            Iterator<Exponente> iteradorExponente = exponenteRepo.findAll().iterator();
            while(iteradorExponente.hasNext()){
                Exponente exponenteIterado = iteradorExponente.next();
                String expoApellidos = exponente.getApellidosExponente().toUpperCase();
                String expoNombre =exponente.getNombreExponente().toUpperCase();
                String expoAlias = exponente.getAlias().toUpperCase();
                if(expoNombre.equals(exponenteIterado.getNombreExponente().toUpperCase()) 
                && expoApellidos.equals(exponenteIterado.getApellidosExponente().toUpperCase()) 
                && expoAlias.equals(exponenteIterado.getAlias().toUpperCase())){
                    exponenteABuscar = exponenteIterado;
                    break;
                }
            }
            return exponenteABuscar;
        }

        public Iterable<Exponente> listaExponentes(){
            return exponenteRepo.findAll();
        }


        public void eliminaExponente(Exponente exponente, Actividad actividad){
            for(int i = 0; i< exponente.getActividades().size(); i++){
                if(exponente.getActividades().get(i).equals(actividad)){
                    exponente.getActividades().remove(actividad);
                    guardarExponente(exponente);
                    break;
                }
            }

        }

        public Exponente encuentraExponente(int exponenteId){
            return exponenteRepo.findById(exponenteId).orElse(null);
        }
    
    @Transactional
    public void anadirExponente(Actividad actividad, Exponente exponente) throws DataAccessException{
        if(buscaExponente(exponente)==null){
            List<Actividad> actividades = new ArrayList<>();
            actividades.add(actividad);
            exponente.setActividades(actividades);
            exponenteRepo.save(exponente);

        }else{
            exponente = buscaExponente(exponente);
                if(actividadService.contieneExponente(exponente, actividad)==false){
                    exponente = buscaExponente(exponente);
                    List<Actividad> actividadesActual =exponente.getActividades();
                    actividadesActual.add(actividad);
                    exponente.setActividades(actividadesActual);
                    exponenteRepo.save(exponente);
                }
               
            }
        }


    }