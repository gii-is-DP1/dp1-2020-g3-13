package org.springframework.samples.petclinic.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Consulta;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.repository.ConsultaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private ClienteService clienteService;

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
}
