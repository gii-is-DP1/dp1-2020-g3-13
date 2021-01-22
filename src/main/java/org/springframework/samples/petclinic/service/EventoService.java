package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.repository.EventoRepository;
import org.springframework.stereotype.Service;

@Service
public class EventoService {
    
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private TipoEntradaService tipoEntradaService;
    @Autowired
    private ClienteService clienteService;


    
    public int eventosCount(){
        return (int) eventoRepository.count();
    }

    public Iterable<Evento> findAll(){
        return eventoRepository.findAll();
    }

    @Transactional
	public void save(Evento evento) throws DataAccessException {

        eventoRepository.save(evento);
    }
    
    public void delete(Evento evento){
        eventoRepository.delete(evento);
    }

    public Evento findEventoById(int eventoId) {
        return eventoRepository.findById(eventoId).orElse(null);
    }

    public void modifyEvento(Evento evento, Evento eventoActual) throws DataAccessException{
		evento.setId(eventoActual.getId());
        save(evento);
    }
    
    public List<Evento> listadoEventosDeOrganizacion(int id_organizacion){
        return eventoRepository.listadoEventosDeOrganizacion(id_organizacion);
    }

    public List<Entrada> encontrarEntradasEvento(Evento evento){
        List<Entrada> entradas = new ArrayList<Entrada>();
        List<TipoEntrada> tiposEntrada = evento.getTipoEntradas();
        int i = 0;
        while(i<tiposEntrada.size()){
            entradas.addAll(tipoEntradaService.EncontrarTodasLasEntradas(tiposEntrada.get(i)));

            }
        return entradas; 
        }

    public void anadirEventoAFav(Evento evento, String nombreUsuario){
        Cliente cliente =clienteService.findClienteByUsuario(nombreUsuario);
        if(cliente.getEventosFavoritos()==null){
            List<Evento> listaEventos = new ArrayList<>();
            listaEventos.add(evento);
            
        }else{
            List<Evento> listaActual =cliente.getEventosFavoritos();
            listaActual.add(evento);
        }
        clienteService.saveCliente(cliente);
    }
    public void borrarEventoFav(Evento evento, Cliente cliente){
        for(int i=0; i<cliente.getEventosFavoritos().size();i++){
            if(cliente.getEventosFavoritos().get(i).getId().equals(evento.getId())){
                cliente.getEventosFavoritos().remove(i);
            }        
        }   
        clienteService.saveCliente(cliente);
        
    }

        //Muestra primeros 6 eventos o menos para la página de inicio
        public List<Evento> eventosDeInicio(){
            List<Evento> res = new ArrayList<Evento>();
            Iterator<Evento> iteradorEventos = eventoRepository.findAll().iterator();
            int contador = 0;
            while(iteradorEventos.hasNext() && contador < 6){
                res.add(iteradorEventos.next());
                contador++;
            }
            return res;
        }


    
}
