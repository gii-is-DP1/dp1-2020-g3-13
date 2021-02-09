package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Carrito;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Exponente;
import org.springframework.samples.petclinic.model.LineaFactura;
import org.springframework.samples.petclinic.repository.ActividadRepository;
import org.springframework.samples.petclinic.repository.EventoRepository;
import org.springframework.stereotype.Service;

@Service
public class ActividadService {

    @Autowired
    private ActividadRepository actividadRepo;

    @Autowired
    private AlquilerEspacioService alquilerEspacioService;

    @Autowired
    private CarritoService carritoService;
    
    @Autowired
    private EventoRepository eventoRepo;

    public int actividadesCount() {
        return (int) actividadRepo.count();
    }

    public Iterable<Actividad> findAll() {
        return actividadRepo.findAll();
    }

    public Actividad findById(int id) {
        return actividadRepo.findById(id).orElse(null);
    }

    // Devuelve si cierta actividad contiene al exponente pasado por parametros
    public Boolean contieneExponente(Exponente exponente, Actividad actividad) {
        return actividad.getExponentes().contains(exponente);
    }

    public Actividad encuentraActividadId(int actividadId) {
        return actividadRepo.findById(actividadId).orElse(null);
    }

    @Transactional
    public void guardarActividad(Actividad actividad) {
        actividadRepo.save(actividad);
    }

    @Transactional
    public void borrarAlquileres(Actividad actividad) {
        // AlquilerEspacio alq =actividadRepo.encuentraAlquilerLugar(alquiler.getId());
        actividad.setAlquilerEspacio(null);
    }

    @Transactional
    public Actividad encuentraActividadPorAlquilerId(int alquilerEspacioId) {
        return actividadRepo.encuentraLugarAlquiler(alquilerEspacioId);
    }

    @Transactional
    public void anadirActividadAEvento(Evento evento, Actividad actividad) throws DataAccessException {
        if (eventoRepo.getActividades(evento.getId()) == null) {
            List<Actividad> listaActividades = new ArrayList<>();
            actividad.setEvento(evento);
            listaActividades.add(actividad);
        } else {
            List<Actividad> listaActividadesActual = eventoRepo.getActividades(evento.getId());
            actividad.setEvento(evento);
            listaActividadesActual.add(actividad);
        }
    }

    public List<Actividad> encuentraActividadesPorCarrito(Carrito carrito) {
        List<Actividad> actividades = new ArrayList<Actividad>();
        double total = 0.0;
        if (carrito != null) {
            for (LineaFactura linea : carritoService.dimeLineaFacturasDeCarrito(carrito.getId())) {
                actividades.add(alquilerEspacioService.encuentraActividad(linea.getAlquilerEspacio().getId()));
                total += linea.getPrecio();
            }
            carrito.setTotal(total);
            carritoService.guardarCarrito(carrito);
        }
        return actividades;
    }

        @Transactional
        public void borrarActividad(Actividad actividad){

            actividadRepo.deleteById(actividad.getId());
           
        }
        @Transactional
        public void quitarAlquilerEspacio(Actividad actividad){

            actividad.setAlquilerEspacio(null);
           
        }

    public List<Actividad> encuentraActividadesPorEvento(int eventoId) {
        return actividadRepo.encuentrActividadesEventoId(eventoId);

    }
        @Transactional
        public void modificarActividad(Actividad actividad, Actividad actividadActual) throws DataAccessException{
            actividad.setId(actividadActual.getId());
            actividad.setAlquilerEspacio(actividadActual.getAlquilerEspacio());
            actividad.setExponentes(actividadActual.getExponentes());
            actividad.setEvento(actividadActual.getEvento());
            guardarActividad(actividad);
        }

    public void borraActividadEvento(int eventoId) {
        actividadRepo.deleteAll(encuentraActividadesPorEvento(eventoId));
    }

    @Transactional
    public void eliminaActividadesOrganizacion(int organizacionId){
        actividadRepo.eliminaActividadesOrganizacion(organizacionId);
    }

    public List<Actividad> encuentraActividadesDeOrganizacion(int organizacionId){
        return actividadRepo.encuentraActividadesDeOrganizacion(organizacionId);
    }
}
