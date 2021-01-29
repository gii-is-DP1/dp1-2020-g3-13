package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Consulta;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private EventoService eventoService;

    @Transactional
    public int consultasCount() {
        return (int) consultaRepository.count();
    }

    public Iterable<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    @Transactional
    public void anadirConsulta(Consulta consulta, int evento_id, Cliente cliente) {
        Evento evento = eventoService.findEventoById(evento_id);
        consulta.setEvento(evento);
        consulta.setFechaConsulta(LocalDate.now());
        consulta.setCliente(cliente);

        consultaRepository.save(consulta);

    }
    @Transactional
    //Devuelve todas las consultas que se han realizado a los eventos de 
    //la organizacion cuyo id es el que se ha pasado por parametros
	public List<Consulta> devuelveTodasLasConsultasDeOrganizacionConId(int id) {
    List<Consulta> listaConsultasOrganizacion = new ArrayList<Consulta>();
    Iterator<Consulta> consultas = findAll().iterator();
        while(consultas.hasNext()){
            Consulta consultaIterada = consultas.next();
            if(consultaIterada.getEvento().getOrganizacion().getId().equals(id)){
                listaConsultasOrganizacion.add(consultaIterada);

            }

        }
        return listaConsultasOrganizacion;
}

@Transactional
//Devuelve todas las consultas que se han realizado a los eventos del 
//cliente cuyo id es el que se ha pasado por parametros
public List<Consulta> devuelveTodasLasConsultasDeClienteConId(int id) {
List<Consulta> listaConsultasCliente = new ArrayList<Consulta>();
Iterator<Consulta> consultas = findAll().iterator();
    while(consultas.hasNext()){
        Consulta consultaIterada = consultas.next();
        if(consultaIterada.getCliente().getId().equals(id)){
            listaConsultasCliente.add(consultaIterada);

        }

    }
    return listaConsultasCliente;
}

public Consulta sacaConsultaDeLista(List<Consulta> consultas, int consultaId) {
    Iterator<Consulta> consultasIterador = consultas.iterator();
    Consulta consulta = null;
    while(consultasIterador.hasNext()){
        Consulta consultaIterada = consultasIterador.next();
        if(consultaIterada.getId().equals(consultaId)){
            consulta = consultaIterada;
            break;
        }
    }
	return consulta;
}

public void aniadirRespuesta(Consulta consulta, int consultaId) {

    Iterator<Consulta> consultasIterador = consultaRepository.findAll().iterator();
    while(consultasIterador.hasNext()){
        Consulta consultaIterada = consultasIterador.next();
        if(consultaIterada.getId().equals(consultaId)){
            consultaIterada.setRespuesta(consulta.getRespuesta());
            consultaRepository.save(consultaIterada);
            break;
        }
    }
}
}
