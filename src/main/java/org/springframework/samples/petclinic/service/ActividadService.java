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
import org.springframework.samples.petclinic.repository.AlquilerEspacioRepository;
import org.springframework.stereotype.Service;

@Service
public class ActividadService {

        @Autowired
        private ActividadRepository actividadRepo;

        @Autowired
        private AlquilerEspacioService alquilerEspacioService;
        
        @Autowired
        private CarritoService carritoService;


        public int actividadesCount(){
            return (int) actividadRepo.count();
        }

        public Iterable<Actividad> findAll(){
            return actividadRepo.findAll();
        }

        public Actividad findById(int id){
            return actividadRepo.findById(id).orElse(null);
        }
        public Boolean contieneExponente(Exponente exponente, Actividad actividad){
            Boolean res = false;
            for (int i = 0; i < actividad.getExponentes().size(); i++) {
                if(actividad.getExponentes().get(i).getNombreExponente().equals(exponente.getNombreExponente()) && actividad.getExponentes().get(i).getApellidosExponente().equals(exponente.getApellidosExponente()) && actividad.getExponentes().get(i).getAlias().equals(exponente.getAlias())) {
                    res = true;
                    break;
                }
            }
            return res;
        }
        @Transactional
        public Actividad encuentraActividadId(int actividadId){
            return actividadRepo.findById(actividadId).orElse(null);
        }

        @Transactional
        public void guardarActividad(Actividad actividad){
                actividadRepo.save(actividad);
        }
        @Transactional
        public void borrarAlquileres(Actividad actividad){
            //AlquilerEspacio alq =actividadRepo.encuentraAlquilerLugar(alquiler.getId());
            actividad.setAlquilerEspacio(null);
        }
        @Transactional
        public Actividad encuentraActividadPorAlquilerId(int alquilerEspacioId){
            return actividadRepo.encuentraLugarAlquiler(alquilerEspacioId);
        }



        @Transactional
        public void anadirActividadAEvento(Evento evento, Actividad actividad) throws DataAccessException{

            if(evento.getActividades()==null){
                List<Actividad> listaActividades = new ArrayList<>();
                actividad.setEvento(evento);
                listaActividades.add(actividad);
                evento.setActividades(listaActividades);
            }else{
                List<Actividad> listaActividadesActual = evento.getActividades();
                actividad.setEvento(evento);
                listaActividadesActual.add(actividad);
            }
        }
        // @Transactional
        // public void AñadirLugarRealizacionActividad(Actividad actividad, Integer idLugar) throws DataAccessException{
        //     actividad.setLugarRealizacion(lugarRealizacionService.findById(idLugar));
        // }

		public List<Actividad> encuentraActividadesPorCarrito(Carrito carrito) {
            List<Actividad> actividades = new ArrayList<Actividad>();
            double total = 0.0;
            if(carrito!=null){
                for(LineaFactura linea :carrito.getLineasFacturas()){
                    actividades.add(alquilerEspacioService.encuentraActividad(linea.getAlquilerEspacio().getId()));
                    total += linea.getPrecio();
                    }
                    carrito.setTotal(total);
                    carritoService.guardarCarrito(carrito);
                }
            return actividades;
		}

}
