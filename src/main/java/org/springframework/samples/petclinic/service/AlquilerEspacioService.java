package org.springframework.samples.petclinic.service;

import javax.transaction.Transactional;
import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

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
        double precioTotal = 0.0;
        alquiler.setFechaInicioReserva(actividad.getFechaInicio());
        alquiler.setFechaFinReserva(actividad.getFechaFin());
        actividad.setAlquilerEspacio(alquiler);
        long horas = DAYS.between(alquiler.getFechaInicioReserva(), alquiler.getFechaFinReserva());
        if (horas < 1) {
            precioTotal = lugar.getPrecio() * 1;
        } else {
            precioTotal = lugar.getPrecio() * horas;
        }
        precioTotal = Math.round(precioTotal * 100) / 100d;
        alquiler.setPrecioTotal(precioTotal);
        // actividadService.guardarActividad(actividad);
    }

    @Transactional
    public Actividad encuentraActividad(int alquilerId) {
        return alquilerEspacioRepository.encuentraActividadPorAlquilerId(alquilerId);
    }

    public void compruebaFechas(LugarRealizacion lugarSeleccionado, Actividad actividad, Evento evento)
            throws Exception {
        Iterator<AlquilerEspacio> todosAlquileres = alquilerEspacioRepository.findAll().iterator();     
        while(todosAlquileres.hasNext()) {
            AlquilerEspacio alqActual = todosAlquileres.next();
            if (alqActual.getLugarRealizacion().equals(lugarSeleccionado)) {
                LocalDateTime alqFechaFin = alqActual.getFechaFinReserva();
                LocalDateTime alqFechaInicio = alqActual.getFechaInicioReserva();
                if ((actividad.getFechaInicio().isAfter(alqFechaInicio)
                        && actividad.getFechaInicio().isBefore(alqFechaFin))
                        || (actividad.getFechaFin().isAfter(alqFechaInicio)
                                && actividad.getFechaFin().isBefore(alqFechaFin))
                        || (actividad.getFechaFin().isAfter(alqFechaInicio)
                                && actividad.getFechaFin().isAfter(alqFechaInicio))) {
                    throw new Exception("El lugar seleccionado esta reservado para esas fechas");
                }
            }
        }
    }
}

}