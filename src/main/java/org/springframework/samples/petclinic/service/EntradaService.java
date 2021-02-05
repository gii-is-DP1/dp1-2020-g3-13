package org.springframework.samples.petclinic.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.repository.EntradaRepository;
import org.springframework.samples.petclinic.repository.TipoEntradaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EntradaService {
    @Autowired
    private EntradaRepository entradaRepo;
    @Autowired
    private TipoEntradaRepository tipoEntradaRepo;
    
    @Transactional
    public void crearEntrada(Entrada entrada, Integer tipoEntradaId) throws DataAccessException{
        TipoEntrada tipoEntrada = tipoEntradaRepo.findById(tipoEntradaId).orElse(null);
        entrada.setTipoEntrada(tipoEntrada);
        entradaRepo.save(entrada);
    }

    @Transactional
    public Entrada findEntradaByNombreAsistente(String nombreAsistente){
        return entradaRepo.findEntradaByNombreAsistente(nombreAsistente);
    }

    @Transactional
    public Entrada encuentraEntradaPorId(int entradaId){
        return entradaRepo.findById(entradaId).orElse(null);
    }
    @Transactional
    public Boolean existeElNombreEnElCarro(List<String> listaAsistentes, String nombreAsistente){
        Boolean res = false;
        int i =0;
       
        while(i<listaAsistentes.size()){
            if(listaAsistentes.get(i).equals(nombreAsistente)){
                res= true;
            }
            i++;
        }
        return res;
    }

    @Transactional 
    
    public Boolean buscaPorEventoYPorNombreAsistene(String nombreAsistente, int eventoID){
        List<Entrada> res= new ArrayList<>();
        Boolean aux = false;
        entradaRepo.findAll().iterator().forEachRemaining(res::add);
        for (Entrada entrada2 : res) {
            if(entrada2.getNombreAsistente()==nombreAsistente&&entrada2.getTipoEntrada().getEvento().getId()==eventoID){
                aux = true;
            }

            }
            return aux;
        }
    
   
    
    @Transactional
    public void guardarEntrada(Entrada entrada){
        entradaRepo.save(entrada);
    }

    @Transactional
    public void borrarEntrada(Entrada entrada){
        entradaRepo.delete(entrada);
    }
}
