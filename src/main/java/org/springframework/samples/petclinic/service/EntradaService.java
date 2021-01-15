package org.springframework.samples.petclinic.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.repository.EntradaRepository;
import org.springframework.samples.petclinic.repository.EventoRepository;
import org.springframework.samples.petclinic.repository.TipoEntradaRepository;
import org.springframework.stereotype.Service;
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
}
