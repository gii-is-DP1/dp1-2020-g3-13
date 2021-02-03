package org.springframework.samples.petclinic.service;

import javax.transaction.Transactional;
import static java.time.temporal.ChronoUnit.HOURS;

import java.time.LocalDateTime;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.AlquilerEspacio;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.LugarRealizacion;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.repository.AlquilerEspacioRepository;
import org.springframework.stereotype.Service;

@Service
public class AlquilerEspacioService {

    @Autowired
    private AlquilerEspacioRepository alquilerEspacioRepository;

    @Transactional
    public int alquilerEspacioCount() {
        return (int) alquilerEspacioRepository.count();
    }

    @Transactional
    public Iterable<AlquilerEspacio> findAll() {
        return alquilerEspacioRepository.findAll();
    }

    @Transactional
    public void guardarAlquilerEspacio(AlquilerEspacio alquiler) {
        alquilerEspacioRepository.save(alquiler);
    }

    @Transactional
    public void borrarAlquiler(AlquilerEspacio alquiler) {
        alquilerEspacioRepository.delete(alquiler);
    }

    @Transactional
    public AlquilerEspacio encuentraAlquilerId(int alquilerId) {
        return alquilerEspacioRepository.findById(alquilerId).orElse(null);
    }

    @Transactional
    public void alquilerLugarRealizacion(AlquilerEspacio alquiler, Actividad actividad, Organizacion org) {
        LugarRealizacion lugar = alquiler.getLugarRealizacion();
        alquiler.setFechaInicioReserva(actividad.getFechaInicio());
        alquiler.setFechaFinReserva(actividad.getFechaFin());
        actividad.setAlquilerEspacio(alquiler);
        long horas = HOURS.between(alquiler.getFechaInicioReserva(), alquiler.getFechaFinReserva());
        double precioTotal = lugar.getPrecio() * horas;
        alquiler.setPrecioTotal(precioTotal);
        // actividadService.guardarActividad(actividad);
    }

    @Transactional
    public Actividad encuentraActividad(int alquilerId) {
        return alquilerEspacioRepository.encuentraActividadPorAlquilerId(alquilerId);
    }


    public void compruebaFechas(LugarRealizacion lugarSeleccionado, Actividad actividad, Evento evento) throws Exception{
        Iterator<AlquilerEspacio> todosAlquileres = alquilerEspacioRepository.findAll().iterator();
        if(todosAlquileres.hasNext()){
            AlquilerEspacio alqActual =todosAlquileres.next();
            if(alqActual.getLugarRealizacion()==lugarSeleccionado && actividad.getEvento() !=evento){
               LocalDateTime alqFechaFin = alqActual.getFechaFinReserva();
               LocalDateTime alqFechaInicio = alqActual.getFechaInicioReserva();
               if(alqFechaInicio.isBefore(actividad.getFechaFin()) || alqFechaFin.isAfter(actividad.getFechaInicio())
                || (actividad.getFechaFin().isAfter(alqFechaInicio) && actividad.getFechaFin().isBefore(alqFechaFin))){
                    throw new Exception("El lugar seleccionado esta reservado para esas fechas");
                }
            }
        }

    }

}