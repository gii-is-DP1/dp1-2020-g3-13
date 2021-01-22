package org.springframework.samples.petclinic.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Carrito;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.model.VentaEntrada;
import org.springframework.samples.petclinic.repository.EntradaRepository;
import org.springframework.samples.petclinic.repository.VentaEntradaRepository;
import org.springframework.stereotype.Service;

@Service
public class VentaEntradaService {

    @Autowired
    private VentaEntradaRepository ventaEntradaRepository;
    @Autowired
    private CarritoService carritoService;
    @Autowired
    private EntradaRepository entradaRepo;
    @Autowired
    private TipoEntradaService tipoEntrSer;

    @Transactional
    public long ventaEntradaCount() {
        return ventaEntradaRepository.count();
    }

    @Transactional
    public void actualizaTipoEntrada(Carrito carrito, int lineaId){
            TipoEntrada tipoEntrada= carrito.getLineasFacturas().get(lineaId).getEntrada().getTipoEntrada();
            Evento ev = tipoEntrada.getEvento();
            Integer numActual = tipoEntrada.getNumEntradas();
            numActual = numActual-1;
            tipoEntrada.setNumEntradas(numActual);
            tipoEntrSer.guardar(tipoEntrada);
            carritoService.guardarCarrito(carrito);
        
    }

    @Transactional
    public void finalizarCompra(int carritoId, Cliente cliente, VentaEntrada ventaEntrada) throws DataAccessException {
        Carrito carrito = carritoService.findCarritoById(carritoId);
        Entrada entrada = new Entrada();
        for (int i = 0; i < carrito.getLineasFacturas().size(); i++) {
            entrada = carrito.getLineasFacturas().get(i).getEntrada();
            entradaRepo.save(entrada);
            TipoEntrada tipoEntrada = entrada.getTipoEntrada();
            tipoEntrada.setNumEntradas(tipoEntrada.getNumEntradas()-1);
            tipoEntrSer.guardar(tipoEntrada);
            
        }
        carritoService.generarFacturaCarrito(carrito, cliente);
        carritoService.actualizaCarritoAcero(carrito);
    }
    

    @Transactional
    public void guardaVentaEntrada(VentaEntrada ventaEntrada) throws DataAccessException {
        // List<VentaEntrada> entradas =
        // eventoService.findEventoById(ventaEntrada.getEvento().getId()).getVentaEntrada();
        // Boolean existe = false;
        // int i = 0;
        // while(i<entradas.size()&&existe==false){
        // if(entradas.get(i).getNombreAsistente()==ventaEntrada.getNombreAsistente()){
        // existe = true;
        // }
        // }
        // if(existe== true){
        ventaEntradaRepository.save(ventaEntrada);
        // }
    }

}
