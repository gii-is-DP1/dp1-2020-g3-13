package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Carrito;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.model.VentaEntrada;
import org.springframework.samples.petclinic.repository.VentaEntradaRepository;
import org.springframework.stereotype.Service;

@Service
public class VentaEntradaService {
    
    @Autowired
    private VentaEntradaRepository ventaEntradaRepository;
    @Autowired
    private CarritoService carritoService;




    @Transactional
    public void finalizarCompra(int carritoId, Cliente cliente, VentaEntrada ventaEntrada) throws DataAccessException{
            Carrito carrito = carritoService.findCarritoById(carritoId);
            Entrada entrada = new Entrada();
            for (int i = 0; i < carrito.getLineasFacturas().size(); i++) {
                TipoEntrada lineaActual = carrito.getLineasFacturas().get(i).getTipoEntrada();
                //lineaActual.setNumEntradas(lineaActual.getNumEntradas()-1);
                entrada =carrito.getLineasFacturas().get(i).getEntrada();  
                ventaEntrada.setEntrada(entrada);

            }
            carritoService.generarFacturaCarrito(carrito, cliente);

       


    }

    @Transactional
    public void saveEntrada(VentaEntrada ventaEntrada) throws DataAccessException{
        System.out.println("no lo hace");
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
        System.out.println("si lo hace");
        //}
    }
}
