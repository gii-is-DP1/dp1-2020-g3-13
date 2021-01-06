package org.springframework.samples.petclinic.service.exceptions;

import java.util.List;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.service.EntradaService;
import org.springframework.samples.petclinic.service.EventoService;

public class NombreAsistenteRepetidoException extends Exception{
    @Autowired
    EntradaService entradaService;
    @Autowired
    EventoService eventoService;

    public boolean estaRepetido(Entrada entrada, ConstraintValidatorContext contexto){
        Evento evento = entrada.getTipoEntrada().getEvento();
        List<Entrada> entradas = eventoService.encontrarEntradasEvento(evento);
        int i = 0;
        while(i<entradas.size()){
            if(entradas.get(i).getNombreAsistente().equals(entrada.getNombreAsistente()))
            return true;
            
        }
        return false;
    }
}
