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
    private EntradaService entradaService;
    @Autowired
    private TipoEntradaService tipoEntrSer;

    @Transactional
    public long ventaEntradaCount() {
        return ventaEntradaRepository.count();
    }

    @Transactional
    //Guarda cada entrada que hay en el carrito, reduce los numeros de entrada disponibles y vacía el carrito
    public void finalizarCompra(int carritoId, Cliente cliente, VentaEntrada ventaEntrada) throws DataAccessException {
        Carrito carrito = carritoService.findCarritoById(carritoId);
        Entrada entrada = new Entrada();
        for (int i = 0; i < carrito.getLineasFacturas().size(); i++) {
            entrada = carrito.getLineasFacturas().get(i).getEntrada();
            entradaService.guardarEntrada(entrada);
            TipoEntrada tipoEntrada = entrada.getTipoEntrada();
            tipoEntrada.setNumEntradas(tipoEntrada.getNumEntradas()-1);
            tipoEntrSer.guardar(tipoEntrada);
            
        }
        //Genera la factura del cliente
        carritoService.generarFacturaCarrito(carrito, cliente);
        //Vacía el carrito para una nueva compra
        carritoService.actualizaCarritoAcero(carrito);
    }
    

    @Transactional
    public void guardaVentaEntrada(VentaEntrada ventaEntrada) throws DataAccessException {
        ventaEntradaRepository.save(ventaEntrada);
    }

}
