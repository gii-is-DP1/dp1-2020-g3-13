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

    // @Transactional
    // public Exponente encuentraExponente(Exponente exponente){
    //     String alias = exponente.getAlias();
    //     String apellidos = exponente.getApellidosExponente();
    //     String nombre = exponente.getNombreExponente();
    //     if(exponenteRepo.existeExponenteConEstosAtributos(nombre, apellidos, alias) >= 1){
    //         return exponente;
    //         }else{
    //             return null;
    //         }
    //     }

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

    @Transactional
    public void anadirExponente(Actividad actividad, Exponente exponente) throws DataAccessException{
        if(buscaExponente(exponente)==null){
            actividad.getExponentes().add(exponente);
            List<Actividad> actividades = new ArrayList<>();
            actividades.add(actividad);
            exponente.setActividades(actividades); 
            System.out.println(exponente.getActividades().size());
            exponenteRepo.save(exponente);

        }else{
            exponente = buscaExponente(exponente);
                if(actividadService.contieneExponente(exponente, actividad)==false){
                    exponente = buscaExponente(exponente);
                    actividad.getExponentes().add(exponente);
                    List<Actividad> actividadesActual =exponente.getActividades();
                    System.out.println(exponente.getActividades().size());
                    actividadesActual.add(actividad);
                    exponente.setActividades(actividadesActual);
                }
               
            }
        }


    }