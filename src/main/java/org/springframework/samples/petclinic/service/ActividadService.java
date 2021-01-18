package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Exponente;
import org.springframework.samples.petclinic.repository.ActividadRepository;
import org.springframework.stereotype.Service;

@Service
public class ActividadService {

        @Autowired
        private ActividadRepository actividadRepo;
        @Autowired
        private LugarRealizacionService lugarRealizacionService;
        @Autowired
        private ExponenteService exponenteService;

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
        }
        @Transactional
        public void AñadirLugarRealizacionActividad(Actividad actividad, Integer idLugar) throws DataAccessException{
            actividad.setLugarRealizacion(lugarRealizacionService.findById(idLugar));
        }

}
