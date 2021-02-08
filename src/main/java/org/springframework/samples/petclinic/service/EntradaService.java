package org.springframework.samples.petclinic.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.repository.EntradaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EntradaService {
    @Autowired
    private EntradaRepository entradaRepo;
    @Autowired
    private TipoEntradaService tipoEntradaService;
    
    @Transactional
    public void crearEntrada(Entrada entrada, Integer tipoEntradaId, Cliente cliente) throws DataAccessException{
        TipoEntrada tipoEntrada = tipoEntradaService.findById(tipoEntradaId);
        entrada.setTipoEntrada(tipoEntrada);
        entrada.setCliente(cliente);
        entradaRepo.save(entrada);
    }


    public Entrada findEntradaByNombreAsistente(String nombreAsistente){
        return entradaRepo.findEntradaByNombreAsistente(nombreAsistente);
    }

    public Entrada encuentraEntradaPorId(int entradaId){
        return entradaRepo.findById(entradaId).orElse(null);
    }


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

    
    public Boolean buscaPorEventoYPorNombreAsistene(String nombreAsistente, int eventoID){
        List<Entrada> res= new ArrayList<>();
        Boolean aux = false;
        entradaRepo.findAll().iterator().forEachRemaining(res::add);
        for (Entrada entrada2 : res) {
            if(entrada2.getNombreAsistente().equals(nombreAsistente)&&entrada2.getTipoEntrada().getEvento().getId().equals(eventoID)){
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
    public List<Entrada> buscaEntradaPorClienteId(int ClienteId){
       return entradaRepo.findEntradaByClienteId(ClienteId);
    }
}
