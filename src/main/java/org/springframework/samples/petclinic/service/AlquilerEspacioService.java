package org.springframework.samples.petclinic.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import static java.time.temporal.ChronoUnit.HOURS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.AlquilerEspacio;
import org.springframework.samples.petclinic.model.LugarRealizacion;
import org.springframework.samples.petclinic.repository.AlquilerEspacioRepository;
import org.springframework.stereotype.Service;

@Service
public class AlquilerEspacioService {
    

    @Autowired
    private AlquilerEspacioRepository alquilerEspacioRepository;
    @Autowired
    private ActividadService actividadService;
    @Autowired
    private AlquilerEspacioService alquilerService;

    @Transactional
    public int alquilerEspacioCount(){
        return (int) alquilerEspacioRepository.count();
    }
    @Transactional
    public Iterable<AlquilerEspacio> findAll(){
        return alquilerEspacioRepository.findAll();
    }
    @Transactional 
    public void guardarAlquilerEspacio(AlquilerEspacio alquiler){
        alquilerEspacioRepository.save(alquiler);
    }

    @Transactional
    public AlquilerEspacio encuentraAlquilerId(int alquilerId){
        return alquilerEspacioRepository.findById(alquilerId).orElse(null);
    }

    @Transactional
    public void alquilerLugarRealizacion(AlquilerEspacio alquiler, Actividad actividad){
    //     List<AlquilerEspacio> lista = new ArrayList<>();
    //     actividad.getLugarRealizacion().setAlquilerEspacio(lista);
    //     //List<AlquilerEspacio> lista = actividad.getLugarRealizacion().getAlquilerEspacio();
    //     System.out.println("==================================================================");
    //    if(actividad.getLugarRealizacion().getAlquilerEspacio()!=null){
    
    //     if(actividad.getLugarRealizacion().getAlquilerEspacio().contains(alquiler)){
    //         actividad.getLugarRealizacion().getAlquilerEspacio().remove(alquiler);
    //             actividadService.guardarActividad(actividad);
    //         }
    //    }
        // if(lista.contains(alquiler)){
        //     lista.remove(alquiler);
        //     //actividadService.guardarActividad(actividad);
        // }
        LugarRealizacion lugar = alquiler.getLugarRealizacion();
        alquiler.setFechaInicioReserva(actividad.getFechaInicio());
        alquiler.setFechaFinReserva(actividad.getFechaFin());
        actividad.setLugarRealizacion(lugar);
        long horas = HOURS.between(alquiler.getFechaInicioReserva(), alquiler.getFechaFinReserva());
        double precioTotal = lugar.getPrecio() * horas;
        alquiler.setPrecioTotal(precioTotal);
        // guardarAlquilerEspacio(alquiler);
        // actividadService.guardarActividad(actividad);
    }


}