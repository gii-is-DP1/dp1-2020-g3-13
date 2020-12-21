package org.springframework.samples.petclinic.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Carrito;
import org.springframework.samples.petclinic.model.VentaEntrada;
import org.springframework.samples.petclinic.repository.VentaEntradaRepository;
import org.springframework.stereotype.Service;

@Service
public class VentaEntradaService {
    
    @Autowired
    private VentaEntradaRepository ventaEntradaRepository;


    // @Transactional
    // public void finalizarCompra(Carrito carrito) throws DataAccessException{
    //     ventaEntradaRepository.
    // }

    @Transactional
    public void saveEntrada(VentaEntrada ventaEntrada) throws DataAccessException{
        //List<VentaEntrada> entradas = eventoService.findEventoById(ventaEntrada.getEvento().getId()).getVentaEntrada();
        //Boolean existe = false;
        //int i = 0;
        //while(i<entradas.size()&&existe==false){
            //if(entradas.get(i).getNombreAsistente()==ventaEntrada.getNombreAsistente()){
                //existe = true;
            //}
        //}
        //if(existe== true){
        ventaEntradaRepository.save(ventaEntrada);
        //}
    }
}
