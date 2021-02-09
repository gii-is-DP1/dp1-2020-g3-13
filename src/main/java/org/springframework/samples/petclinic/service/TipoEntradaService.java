package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.repository.TipoEntradaRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoEntradaService {

    @Autowired
    private TipoEntradaRepository tipoEntradaRepository;

    public int tipoEntradaCount() {
        return (int) tipoEntradaRepository.count();
    }

    public Iterable<TipoEntrada> findAll() {
        return tipoEntradaRepository.findAll();
    }

    public TipoEntrada findById(int id_tipoEntrada) {
        return tipoEntradaRepository.findById(id_tipoEntrada).get();
    }

    public void anadirTipoEntrada(Evento evento, TipoEntrada tipoEntrada) {
        tipoEntrada.setEvento(evento);
    }

    public List<Entrada> EncontrarTodasLasEntradas(TipoEntrada tipoEntrada) {
        return tipoEntradaRepository.findAllEntradas(tipoEntrada.getId());
    }

    @Transactional
    public void guardar(TipoEntrada tipoEntrada) {
        tipoEntradaRepository.save(tipoEntrada);
    }

    public void soloVentaAl90PorCiento(TipoEntrada tipoEntrada) {
        tipoEntrada.setNumEntradas((int) (tipoEntrada.getNumEntradas() * 0.9));
    }

    public List<TipoEntrada> devuelveTodasLasEntradasParaElLugar(Integer id) {
        Iterator<TipoEntrada> tiposEntradaIterador = tipoEntradaRepository.findAll().iterator();
        List<TipoEntrada> res = new ArrayList<TipoEntrada>();
        while (tiposEntradaIterador.hasNext()) {
            TipoEntrada tipoEntrada = tiposEntradaIterador.next();
            for (int i = 0; i < tipoEntrada.getActividades().size(); i++) {
                if (tipoEntrada.getActividades().get(i).getId().equals(id)) {
                    res.add(tipoEntrada);
                }
            }

        }
        return res;
    }

    @Transactional
    public void eliminaTipoEntradaDeOrganizacion(int organizacionId) {
        tipoEntradaRepository.eliminaTipoEntradaDeOrganizacion(organizacionId);
    }

    
    @Transactional
    public void eliminaTipoEntradaEvento(int eventoId){
        tipoEntradaRepository.eliminaTipoEntradaDeEvento(eventoId);
        
    }

    public List<TipoEntrada> encuentraTodasLasEntradasDeEvento(int eventoId){
        return tipoEntradaRepository.encuentraTodasLasEntradasDeEvento(eventoId);

    }

}