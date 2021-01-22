package org.springframework.samples.petclinic.service;

import java.time.LocalDateTime;

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
    public void alquilerLugarRealizacion(AlquilerEspacio alquiler, Actividad actividad){
        //LugarRealizacion lugarRealizacion = actividad.getLugarRealizacion();
        LugarRealizacion lugar = alquiler.getLugarRealizacion();
        //alquiler.setLugarRealizacion(lugarRealizacion);
        alquiler.setFechaInicioReserva(actividad.getFechaInicio());
        alquiler.setFechaInicioReserva(actividad.getFechaFin());
        actividad.setLugarRealizacion(lugar);
        long horas = HOURS.between(alquiler.getFechaInicioReserva(), alquiler.getFechaFinReserva());
        double precioTotal = lugar.getPrecio() * horas;
        alquiler.setPrecioTotal(precioTotal);
        guardarAlquilerEspacio(alquiler);
        actividadService.guardarActividad(actividad);
    }


}