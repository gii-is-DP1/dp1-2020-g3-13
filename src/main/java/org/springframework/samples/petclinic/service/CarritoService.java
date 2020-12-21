package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Carrito;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.LineaFactura;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.repository.CarritoRepository;
import org.springframework.samples.petclinic.repository.FacturaRepository;
import org.springframework.stereotype.Service;

@Service
public class CarritoService {
 
    @Autowired
    private CarritoRepository carritoRepo;
    @Autowired
    private FacturaRepository facturaRepo;
    @Autowired
    private FacturaService facturaService;
    @Autowired
    private LineaFacturaService lineaService;


    @Transactional
    public void anadirCarrito(Entrada entrada, Cliente cliente) throws DataAccessException{
        LineaFactura linea = new LineaFactura();
        linea.setCantidad(1);
        linea.setPrecio(entrada.getTipoEntrada().getPrecio());
        linea.setTipoEntrada(entrada.getTipoEntrada());
        Carrito carrito = new Carrito();
       for (int i = 0; i < carritoRepo.count(); i++) {
          if(cliente == carritoRepo.findById(i).get().getCliente()){
            carrito = carritoRepo.findById(i).get();
            carrito.getLineasFacturas().add(linea);
            break;

          }
           
       }

       if(carrito.getCliente()==null){
           carrito.setCliente(cliente);
           List<LineaFactura> lineasFacturas = new ArrayList<>();
           lineasFacturas.add(linea);
           carrito.setLineasFacturas(lineasFacturas);
       }
      
       carritoRepo.save(carrito);
    }

    @Transactional
    public Factura generarFacturaCarrito(Carrito carrito, Cliente cliente) throws DataAccessException{
       Factura factura = new Factura();
       List<LineaFactura> lineas =  carrito.getLineasFacturas();
       factura.setLineasFacturas(lineas);
       Integer cont = 0;
       Double precioTotal = 0.0;
       Integer numLinea = lineas.size();
       while(cont < numLinea){
        precioTotal = precioTotal +lineas.get(cont).getPrecio();
        cont++;
       }
        factura.setPrecioTotal(precioTotal);
        factura.setFechaFactura(LocalDate.now());
        String usuarioAsocidado = cliente.getUsuario().getNombreUsuario();
        factura.setUsuarioAsocidado(usuarioAsocidado);
        return factura;
    }
    

}
