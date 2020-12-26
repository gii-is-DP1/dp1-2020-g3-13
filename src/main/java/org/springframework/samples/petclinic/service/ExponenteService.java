package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.Iterator;
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
    private ExponenteService exponenteService;
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
        if(exponenteRepo.existeExponenteConEsteAlias(alias) >= 1  
        && exponenteRepo.existeExponenteConEsteApellidos(apellidos) >= 1  
        && exponenteRepo.existeExponenteConEsteNombre(nombre) >= 1 ){

            return exponente;
            }else{
                return null;
            }
        
        }

        public Exponente buscaExponente(Exponente exponente){
            Exponente exponenteABuscar = null;
            Iterator<Exponente> iteradorExponente = exponenteRepo.findAll().iterator();
            while(iteradorExponente.hasNext()){
                Exponente exponenteIterado = iteradorExponente.next();
                System.out.println("hhhh ********************");
                if(exponente.getNombreExponente().equals(exponenteIterado.getNombreExponente())){
                    System.out.println("ENTRE ****************");
                    exponenteABuscar = exponenteIterado;
                    break;
                }
            }
            return exponenteABuscar;
        }

    @Transactional
    public void anadirExponente(Actividad actividad, Exponente exponente) throws DataAccessException{
        if(exponenteRepo.existeExponenteConEsteNombre(exponente.getNombreExponente()) <= 0 
        || exponenteRepo.existeExponenteConEsteApellidos(exponente.getApellidosExponente()) <= 0 
        || exponenteRepo.existeExponenteConEsteAlias(exponente.getAlias()) <= 0 ){
            System.out.println("********************************** NO EXISTE ******************************");
            actividad.getExponentes().add(exponente);
            List<Actividad> actividades = new ArrayList<>();
            actividades.add(actividad);
            exponente.setActividades(actividades); 
            System.out.println(exponente.getActividades().size());
            exponenteRepo.save(exponente);

        }else{
            if(encuentraExponente(exponente)!=null){
                System.out.println("********************************** EXISTE ******************************");
                if(actividadService.contieneExponente(exponente, actividad)==false){
                   //PROBAR SIN ESTE METDODO 
                    exponente = exponenteService.buscaExponente(exponente);
                    //
                    System.out.println("*****************");
                    actividad.getExponentes().add(exponente);
                    System.out.println("*****************1");
                    System.out.println(exponente.getNombreExponente());
                    List<Actividad> actividadesActual =exponente.getActividades();
                    System.out.println("*****************2");
                    System.out.println(exponente.getActividades().size());
                    actividadesActual.add(actividad);
                    System.out.println("*****************3");
                    exponente.setActividades(actividadesActual);
                }
               
            }
        }
    }


    }