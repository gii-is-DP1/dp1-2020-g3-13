package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.AlquilerEspacio;
import org.springframework.samples.petclinic.model.Carrito;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.LineaFactura;
import org.springframework.samples.petclinic.model.Organizacion;
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
    private LineaFacturaService lineaService;


    @Transactional
    public long carritoCount(){
        return carritoRepo.count();
    }

    public Carrito listadoObjetosCarrito(String nombreUsuario){
       return carritoRepo.dimeCarritoDeUsuario(nombreUsuario);
    }

    public Carrito listadoObjetosCarritoOrganizacion(String nombreUsuario){
       return carritoRepo.dimeCarritoDeUsuarioOrganizacion(nombreUsuario);
    }

    public Integer contadorElementosCarrito(Carrito carrito){
        return carritoRepo.dimeLineaFacturaCarrito(carrito.getId()).size();
    }
    public Carrito dimeCarritoOrganizacion(String nombreUsuario){
        return carritoRepo.dimeCarritoDeUsuarioOrganizacion(nombreUsuario);
    }

    public Carrito dimeCarritoUsuario(String nombreUsuario){
        return carritoRepo.dimeCarritoDeUsuario(nombreUsuario);
    }

    @Transactional
    public void actualizaCarritoAcero(Carrito carrito){
      for (LineaFactura  lf : carritoRepo.dimeLineaFacturaCarrito(carrito.getId())) {
        lineaService.borrarLinea(lf);
      } 
        carrito.setTotal(0.0);
        carritoRepo.save(carrito);
    
        }

    @Transactional
    public void anadirCarrito(Entrada entrada, Cliente cliente) throws DataAccessException{
        LineaFactura linea = new LineaFactura();
        linea.setCantidad(1);
        linea.setPrecio(entrada.getTipoEntrada().getPrecio());
    //    linea.setTipoEntrada(entrada.getTipoEntrada());
        // entrada.setLineaFactura(linea);
        entrada.setCliente(cliente);
        linea.setEntrada(entrada);
        Carrito carrito = new Carrito();


        
        if(carritoRepo.dimeCarritoDeUsuario(cliente.getUsuario().getNombreUsuario()) != null){
                carrito = carritoRepo.dimeCarritoDeUsuario(cliente.getUsuario().getNombreUsuario());
                linea.setCarrito(carrito);
                
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaa");
                carrito.setCliente(cliente);
                guardarCarrito(carrito);
                System.out.println(carrito.getTotal());
        }else{
            linea.setCarrito(carrito);
            carrito.setCliente(cliente);
            List<LineaFactura> lineasFacturas = new ArrayList<>();
            lineasFacturas.add(linea);
           
            guardarCarrito(carrito);
            
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
            System.out.println(carrito.getTotal());

        }
         lineaService.save(linea);

        double total = 0.0;
        for (int i = 0; i < carritoRepo.dimeLineaFacturaCarrito(carrito.getId()).size(); i++) {
            total += carritoRepo.dimeLineaFacturaCarrito(carrito.getId()).get(i).getPrecio();
        }
        carrito.setTotal(total);
        carritoRepo.save(carrito);
    }

    @Transactional
    public void anadirCarritoLugarRealizacion(Actividad actividad, Organizacion organizacion) throws DataAccessException{
        AlquilerEspacio alquiler = actividad.getAlquilerEspacio();
        LineaFactura linea = new LineaFactura();


        linea.setCantidad(1);
        linea.setPrecio(alquiler.getPrecioTotal());
        linea.setAlquilerEspacio(alquiler);
        Carrito carrito = new Carrito();
        Double total = 0.;
        if(carritoRepo.dimeCarritoDeUsuarioOrganizacion(organizacion.getUsuario().getNombreUsuario()) != null){
            carrito = carritoRepo.dimeCarritoDeUsuarioOrganizacion(organizacion.getUsuario().getNombreUsuario());
            linea.setCarrito(carrito);
            System.out.println("megustaelfutbolmucho");
            
            carritoRepo.dimeLineaFacturaCarrito(carrito.getId()).add(linea);     
            System.out.println("petas? no cigarro");
            for (int i = 0; i < carritoRepo.dimeLineaFacturaCarrito(carrito.getId()).size(); i++) {
                total += carritoRepo.dimeLineaFacturaCarrito(carrito.getId()).get(i).getPrecio();
            }
            total = Math.round(total * 100)/100d;
            }else{
             
                carrito.setOrganizacion(organizacion);
                List<LineaFactura> lineasFacturas = new ArrayList<>();
                linea.setCarrito(carrito);
                lineasFacturas.add(linea);
            }
            lineaService.save(linea);
            carrito.setTotal(total);
            carritoRepo.save(carrito);
        }
 
    public Carrito findCarritoById(int carritoId) throws DataAccessException{
        return carritoRepo.findById(carritoId).orElse(null);
    }

    @Transactional
    public void deleteCarrito(Carrito carrito) throws DataAccessException{
        carritoRepo.delete(carrito);
    }

    @Transactional
    public void borrarLineaFactura(Carrito carrito, int lineaFacturaId) throws DataAccessException{
        LineaFactura linea = lineaService.encuentraLineaFactura(lineaFacturaId);
        carritoRepo.dimeLineaFacturaCarrito(carrito.getId()).remove(linea);

    }

    public void generarFacturaCarrito(Carrito carrito, Cliente cliente) throws DataAccessException{
       Factura factura = new Factura();
       List<LineaFactura> lineas =  carritoRepo.dimeLineaFacturaCarrito(carrito.getId());;
       List<LineaFactura> listaLineasDeFactura = new ArrayList<LineaFactura>();
    //    factura.setLineasFacturas(listaLineasDeFactura);
       Integer cont = 0;
       Double precioTotal = 0.0;
       Integer numLinea = lineas.size();
       while(cont < numLinea){
        precioTotal = precioTotal +lineas.get(cont).getPrecio();
        cont++;
       }
        precioTotal = Math.round(precioTotal * 100)/100d;
        factura.setPrecioTotal(precioTotal);
        factura.setFechaFactura(LocalDate.now());
        factura.setUsuario(cliente.getUsuario()); 
      //  factura.setLineasFacturas(lineas);
        for (LineaFactura lineaFactura : lineas) {
            lineaFactura.setFactura(factura);
        }
        facturaRepo.save(factura);

    }

    public void generarFacturaCarritoOrg(Carrito carrito, Organizacion org) throws DataAccessException{
       Factura factura = new Factura();
       List<LineaFactura> lineas =  carritoRepo.dimeLineaFacturaCarrito(carrito.getId());
       Integer cont = 0;
       Double precioTotal = 0.0;
       Integer numLinea = lineas.size();
       while(cont < numLinea){
        precioTotal = precioTotal +lineas.get(cont).getPrecio();
        cont++;
       }
        precioTotal = Math.round(precioTotal * 100)/100d;
        factura.setPrecioTotal(precioTotal);
        factura.setFechaFactura(LocalDate.now());
        factura.setUsuario(org.getUsuario()); 
      //  factura.setLineasFacturas(lineas);
        for (LineaFactura lineaFactura : lineas) {
            lineaFactura.setFactura(factura);
        }
        facturaRepo.save(factura);

    }
    public List<String> dimeNombreAsistentes(Carrito car,int eventoId){
        if(car==null){
            return new ArrayList<>();
        }else{
        List<String>nombreAsist= new ArrayList<>();
        List<LineaFactura> lf=  carritoRepo.dimeLineaFacturaCarrito(car.getId());
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
    public List<LineaFactura> dimeLineaFacturasDeCarrito(int carritoId){
        return carritoRepo.dimeLineaFacturaCarrito(carritoId);
    }
}