package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.repository.ActividadRepository;
import org.springframework.stereotype.Service;

@Service
public class ActividadService {

        @Autowired
        private ActividadRepository actividadRepo;

        @Transactional
        public int actividadesCount(){
            return (int) actividadRepo.count();
        }

        public Iterable<Actividad> findAll(){
            return actividadRepo.findAll();
        }

        @Autowired
        private EventoService eventoService;

        @Transactional
        public void guardarActividad(Actividad actividad){
            actividadRepo.save(actividad);
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
            //eventoService.save(evento);
        }

}
