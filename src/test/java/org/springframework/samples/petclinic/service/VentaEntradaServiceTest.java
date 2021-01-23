package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Carrito;
import org.springframework.samples.petclinic.model.LineaFactura;
import org.springframework.samples.petclinic.model.VentaEntrada;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class VentaEntradaServiceTest {

    @Autowired
    private VentaEntradaService ventaEntradaService;
    @Autowired
    private CarritoService carritoService;
    @Autowired
    private LineaFacturaService lineaFacturaService;

    @Test
    public void contarDatosIniciales() {
        int count = (int) ventaEntradaService.ventaEntradaCount();
        assertEquals(count, 0);
    }

    // TODO !**************************** HAY QUE TERMINARLO CON EL NUMERO DE
    // ENTRADAS,
    // ESTE METODO NO ESTÁ COMPROBANDOLO TODO
    @Test
    public void deberiaFinalizarCompra() {
        // Creacion de dos lineas de factura
        LineaFactura linea1 = new LineaFactura();
        linea1.setCantidad(1);
        linea1.setPrecio(15.0);
        linea1.setId(lineaFacturaService.lineaFacturaCount() + 1);
        lineaFacturaService.save(linea1);
        LineaFactura linea2 = new LineaFactura();
        linea2.setCantidad(1);
        linea2.setPrecio(15.0);
        linea2.setId(lineaFacturaService.lineaFacturaCount() + 1);
        lineaFacturaService.save(linea2);
        // Añade las linea de factura a una lista
        List<LineaFactura> lineas = new ArrayList<LineaFactura>();
        lineas.add(linea1);
        lineas.add(linea2);
        // Creación de un carrito
        Carrito carrito = new Carrito();
        carrito.setId(((int) carritoService.carritoCount()) + 1);
        carrito.setLineasFacturas(lineas);
        carrito.setTotal(30.0);
        // Creacion de ventaEntrada

        VentaEntrada ventaEntrada = new VentaEntrada();
        int numeroVentaEntradaAntes = (int) ventaEntradaService.ventaEntradaCount();
        ventaEntrada.setCvv("111");
        ventaEntrada.setFechaCaducidad(LocalDate.now().plusDays(30));
        ventaEntrada.setId(((int) ventaEntradaService.ventaEntradaCount()) + 1);
        ventaEntrada.setNombreTitular("nombreTitular");
        ventaEntrada.setNumTarjeta("1111222233334444");
        ventaEntradaService.guardaVentaEntrada(ventaEntrada);
        int numeroVentaEntradaDespues = (int) ventaEntradaService.ventaEntradaCount();
        assertEquals(numeroVentaEntradaAntes + 1, numeroVentaEntradaDespues);
    }

}
