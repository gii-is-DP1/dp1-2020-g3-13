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
    public AlquilerEspacio encuentraAlquilerId(int alquilerId){
        return alquilerEspacioRepository.findById(alquilerId).orElse(null);
    }

    @Transactional
    public void alquilerLugarRealizacion(AlquilerEspacio alquiler, Actividad actividad){
        //LugarRealizacion lugarRealizacion = actividad.getLugarRealizacion();
        LugarRealizacion lugar = alquiler.getLugarRealizacion();
        System.out.println(lugar.getNombre_recinto());
        //alquiler.setLugarRealizacion(lugarRealizacion);
        alquiler.setFechaInicioReserva(actividad.getFechaInicio());
        System.out.println(alquiler.getFechaInicioReserva());
        alquiler.setFechaFinReserva(actividad.getFechaFin());
        System.out.println(alquiler.getFechaFinReserva());
        actividad.setLugarRealizacion(lugar);
        //alquiler.setLugarRealizacion(lugar);
        System.out.println(alquiler.getLugarRealizacion().getNombre_recinto());
        long horas = HOURS.between(alquiler.getFechaInicioReserva(), alquiler.getFechaFinReserva());
        Double precioLugar = lugar.getPrecio();
        double precioTotal = lugar.getPrecio() * horas;
        System.out.println(precioTotal);
        alquiler.setPrecioTotal(precioTotal);
        guardarAlquilerEspacio(alquiler);
        actividadService.guardarActividad(actividad);
    }


}