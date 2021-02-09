package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.AlquilerEspacio;
import org.springframework.samples.petclinic.model.Autoridades;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.LineaFactura;
import org.springframework.samples.petclinic.model.LugarRealizacion;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.stereotype.Service;
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class FacturaServiceTest {
    @Autowired
    private FacturaService facturaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LugarRealizacionService lugarRealizacionService;
    @Autowired
    private AlquilerEspacioService alquilerEspacioService;
    @Autowired
    private OrganizacionService organizacionService;
    @Autowired
    private CarritoService carritoService;
    @Autowired 
    private LineaFacturaService lineaFacturaService;

    @Test
    public void deberiaMostrarLineaFacturaDeFactura(){
         //inicializamos usuario y su autoridad
         Usuario usuario = new Usuario();
         usuario.setNombreUsuario("org1111111");
         usuario.setEnabled(true);
         usuario.setPassword("orgAnizacion1");
         Autoridades autoridades = new Autoridades();
         autoridades.setAutoridad("organizacion");
         usuario.setAutoridades(autoridades);
         usuarioService.saveUser(usuario);
         //inicializamos la organizacion
         Organizacion organizacion = new Organizacion();
         organizacion.setUsuario(usuario);
         organizacion.setCif("18467868486");
         organizacion.setEmail("org1@gmail.com");
         organizacion.setInfo("info de la organizacion");
         organizacion.setNombreOrganizacion("org1");
         organizacionService.saveOrganizacion(organizacion);
        LugarRealizacion lugarRealizacion = new LugarRealizacion();
        lugarRealizacion.setAforo(2000);
		lugarRealizacion.setCaracteristicas("caracteristicas");
		lugarRealizacion.setDireccion("direccion del lugar");
		lugarRealizacion.setEmail("email@email.com");
		lugarRealizacion.setPrecio(100.40);
		lugarRealizacion.setNombre_recinto("nombre_recinto");
		lugarRealizacion.setTelefono("555666777");
		lugarRealizacion.setUrlFoto("https://imagen.com");
        lugarRealizacionService.saveLugarRealizacion(lugarRealizacion);
        //inicializamos alquilerEspacio
        AlquilerEspacio alquilerEspacio = new AlquilerEspacio();
		alquilerEspacio.setPrecioTotal(2000.00);
		alquilerEspacio.setLugarRealizacion(lugarRealizacion);
		alquilerEspacio.setFechaInicioReserva(LocalDateTime.now().plusDays(1));
		alquilerEspacio.setFechaFinReserva(alquilerEspacio.getFechaInicioReserva().plusDays(1));
        alquilerEspacioService.guardarAlquilerEspacio(alquilerEspacio);
        Factura factura = new Factura();
        factura.setFechaFactura(LocalDate.now());
        factura.setUsuario(usuario);
        factura.setPrecioTotal(100.40);
        facturaService.save(factura);
        LineaFactura linea = new LineaFactura();
        linea.setAlquilerEspacio(alquilerEspacio);
        linea.setCantidad(1);
        linea.setPrecio(100.40);
        linea.setCarrito(carritoService.dimeCarritoUsuario(usuario.getNombreUsuario()));
        linea.setFactura(factura);
        lineaFacturaService.save(linea);
        List<LineaFactura> lista = facturaService.lineasFacturaDeFactura(factura.getId());
        assertEquals(1, lista.size());

    }
    @Test
    public void deberiaMostrarFacturaDeUsuario(){
         //inicializamos usuario y su autoridad
         Usuario usuario = new Usuario();
         usuario.setNombreUsuario("org1111111");
         usuario.setEnabled(true);
         usuario.setPassword("orgAnizacion1");
         Autoridades autoridades = new Autoridades();
         autoridades.setAutoridad("organizacion");
         usuario.setAutoridades(autoridades);
         usuarioService.saveUser(usuario);
         //inicializamos la organizacion
         Organizacion organizacion = new Organizacion();
         organizacion.setUsuario(usuario);
         organizacion.setCif("18467868486");
         organizacion.setEmail("org1@gmail.com");
         organizacion.setInfo("info de la organizacion");
         organizacion.setNombreOrganizacion("org1");
         organizacionService.saveOrganizacion(organizacion);
        LugarRealizacion lugarRealizacion = new LugarRealizacion();
        lugarRealizacion.setAforo(2000);
		lugarRealizacion.setCaracteristicas("caracteristicas");
		lugarRealizacion.setDireccion("direccion del lugar");
		lugarRealizacion.setEmail("email@email.com");
		lugarRealizacion.setPrecio(100.40);
		lugarRealizacion.setNombre_recinto("nombre_recinto");
		lugarRealizacion.setTelefono("555666777");
		lugarRealizacion.setUrlFoto("https://imagen.com");
        lugarRealizacionService.saveLugarRealizacion(lugarRealizacion);
        //inicializamos alquilerEspacio
        AlquilerEspacio alquilerEspacio = new AlquilerEspacio();
		alquilerEspacio.setPrecioTotal(2000.00);
		alquilerEspacio.setLugarRealizacion(lugarRealizacion);
		alquilerEspacio.setFechaInicioReserva(LocalDateTime.now().plusDays(1));
		alquilerEspacio.setFechaFinReserva(alquilerEspacio.getFechaInicioReserva().plusDays(1));
        alquilerEspacioService.guardarAlquilerEspacio(alquilerEspacio);
        Factura factura = new Factura();
        factura.setFechaFactura(LocalDate.now());
        factura.setUsuario(usuario);
        factura.setPrecioTotal(100.40);
        facturaService.save(factura);
        LineaFactura linea = new LineaFactura();
        linea.setAlquilerEspacio(alquilerEspacio);
        linea.setCantidad(1);
        linea.setPrecio(100.40);
        linea.setCarrito(carritoService.dimeCarritoUsuario(usuario.getNombreUsuario()));
        linea.setFactura(factura);
        lineaFacturaService.save(linea);
        List<Factura> lista = facturaService.facturasUsuario(usuario);
        assertEquals(1, lista.size());

    }

}
