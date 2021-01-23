package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.AlquilerEspacio;
import org.springframework.samples.petclinic.model.Carrito;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.LineaFactura;
import org.springframework.samples.petclinic.model.LugarRealizacion;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.repository.CarritoRepository;
import org.springframework.samples.petclinic.repository.FacturaRepository;
import org.springframework.samples.petclinic.web.OrganizacionController;
import org.springframework.stereotype.Service;

@Service
public class CarritoService {
 
    @Autowired
    private CarritoRepository carritoRepo;
    @Autowired
    private FacturaRepository facturaRepo;


    @Transactional
    public long carritoCount(){
        return carritoRepo.count();
    }

    @Transactional 
    public Carrito listadoObjetosCarrito(String nombreUsuario){
       return carritoRepo.dimeCarritoDeUsuario(nombreUsuario);
    }

    @Transactional
    public Integer contadorElementosCarrito(Carrito carrito){
        return carrito.getLineasFacturas().size();
    }

    @Transactional
    public Carrito dimeCarritoUsuario(String nombreUsuario){
        return carritoRepo.dimeCarritoDeUsuario(nombreUsuario);
    }

    @Transactional
    public void actualizaCarritoAcero(Carrito carrito){
        carrito.getLineasFacturas().clear();
        carrito.setTotal(0.0);
        carritoRepo.save(carrito);

        }

    @Transactional
    public void anadirCarrito(Entrada entrada, Cliente cliente) throws DataAccessException{
        LineaFactura linea = new LineaFactura();
        linea.setCantidad(1);
        linea.setPrecio(entrada.getTipoEntrada().getPrecio());
        //linea.setTipoEntrada(entrada.getTipoEntrada());
        // entrada.setLineaFactura(linea);
        entrada.setCliente(cliente);
        linea.setEntrada(entrada);
        Carrito carrito = new Carrito();
        

        
        if(carritoRepo.dimeCarritoDeUsuario(cliente.getUsuario().getNombreUsuario()) != null){
                carrito = carritoRepo.dimeCarritoDeUsuario(cliente.getUsuario().getNombreUsuario());
                linea.setCarrito(carrito);
                carrito.getLineasFacturas().add(linea);     
        }else{
            
            carrito.setCliente(cliente);
            List<LineaFactura> lineasFacturas = new ArrayList<>();
            linea.setCarrito(carrito);
            lineasFacturas.add(linea);
            carrito.setLineasFacturas(lineasFacturas);
        }
        double total = 0.0;
        for (int i = 0; i < carrito.getLineasFacturas().size(); i++) {
            total += carrito.getLineasFacturas().get(i).getPrecio();
        }
        carrito.setTotal(total);
        carritoRepo.save(carrito);
    }

    @Transactional
    public void anadirCarritoLugarRealizacion(AlquilerEspacio alquiler, Organizacion organizacion) throws DataAccessException{
        LineaFactura linea = new LineaFactura();
        linea.setCantidad(1);
        linea.setPrecio(alquiler.getPrecioTotal());
        linea.setAlquilerEspacio(alquiler);
        //linea.setTipoEntrada(entrada.getTipoEntrada());
        // entrada.setLineaFactura(linea);
        // entrada.setCliente(cliente);
        //linea.setEntrada(entrada);
        Carrito carrito = new Carrito();
        if(carritoRepo.dimeCarritoDeUsuario(organizacion.getUsuario().getNombreUsuario()) != null){
            carrito = carritoRepo.dimeCarritoDeUsuario(organizacion.getUsuario().getNombreUsuario());
            linea.setCarrito(carrito);
            carrito.getLineasFacturas().add(linea);     
            }else{
                
                carrito.setOrganizacion(organizacion);
                List<LineaFactura> lineasFacturas = new ArrayList<>();
                linea.setCarrito(carrito);
                lineasFacturas.add(linea);
                carrito.setLineasFacturas(lineasFacturas);
            }
            double total = 0.0;
            for (int i = 0; i < carrito.getLineasFacturas().size(); i++) {
                total += carrito.getLineasFacturas().get(i).getPrecio();
            }
            carrito.setTotal(total);
            carritoRepo.save(carrito);
        }
 
    @Transactional
    public Carrito findCarritoById(int carritoId) throws DataAccessException{
        return carritoRepo.findById(carritoId).orElse(null);
    }

    @Transactional
    public void deleteCarrito(Carrito carrito) throws DataAccessException{
        carritoRepo.delete(carrito);
    }



    @Transactional
    public void generarFacturaCarrito(Carrito carrito, Cliente cliente) throws DataAccessException{
       Factura factura = new Factura();
       List<LineaFactura> lineas =  carrito.getLineasFacturas();
       List<LineaFactura> listaLineasDeFactura = new ArrayList<LineaFactura>();
       factura.setLineasFacturas(listaLineasDeFactura);
       Integer cont = 0;
       Double precioTotal = 0.0;
       Integer numLinea = lineas.size();
       while(cont < numLinea){
        precioTotal = precioTotal +lineas.get(cont).getPrecio();
        cont++;
       }
        factura.setPrecioTotal(precioTotal);
        factura.setFechaFactura(LocalDate.now());
        factura.setUsuario(cliente.getUsuario()); 
      //  factura.setLineasFacturas(lineas);
        for (LineaFactura lineaFactura : lineas) {
            lineaFactura.setFactura(factura);
            factura.getLineasFacturas().add(lineaFactura);
        }
        facturaRepo.save(factura);

    }
    @Transactional
    public List<String> dimeNombreAsistentes(Carrito car,int eventoId){
        if(car==null){
            return new ArrayList<>();
        }else{
        List<String>nombreAsist= new ArrayList<>();
        List<LineaFactura> lf=  car.getLineasFacturas();
        int i =0;
        while(lf.size()-1>=i){
            if(lf.get(i).getEntrada().getTipoEntrada().getEvento().getId()==eventoId){
            nombreAsist.add(lf.get(i).getEntrada().getNombreAsistente());
        }
            i++;
        }
        return nombreAsist;
    }

}

    @Transactional
    public void guardarCarrito(Carrito car){
        carritoRepo.save(car);
    }
}