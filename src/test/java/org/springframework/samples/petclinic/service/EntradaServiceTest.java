package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.AlquilerEspacio;
import org.springframework.samples.petclinic.model.Autoridades;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Exponente;
import org.springframework.samples.petclinic.model.InicializadorObjetosTest;
import org.springframework.samples.petclinic.model.LugarRealizacion;
import org.springframework.samples.petclinic.model.NombreTiposEntrada;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.repository.LugarRealizacionRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EntradaServiceTest {
  
    @Autowired
    private EntradaService entradaService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TipoEntradaService TipoEntradaService;
    @Autowired
    private ActividadService actividadService;

    // @Test
    // public void deberiaCrearEntrada(){
    //     Usuario usuario = new Usuario();
    //     usuario.setPassword("Password88");
    //     usuario.setNombreUsuario("nombreUsuario");
    //     usuario.setFacturas(new ArrayList<>());
    //     usuario.setEnabled(true);
    //     usuario.setAutoridades(new Autoridades());
    //     usuarioService.saveUser(usuario);
    //     Cliente cliente = new Cliente();
    //     cliente.setApellidos("apellidos");
    //     cliente.setEmail("email@email.com");
    //     cliente.setEventosFavoritos(new ArrayList<Evento>());
    //     cliente.setId(1);
    //     cliente.setNombre("nombre");
    //     cliente.setTelefono("767656545");
    //     cliente.setUsuario(usuario);
    //     clienteService.saveCliente(cliente);
    //     LugarRealizacion lugarRealizacion = new LugarRealizacion();
    //     lugarRealizacion.setAforo(2000);
	// 	lugarRealizacion.setCaracteristicas("caracteristicas");
	// 	lugarRealizacion.setDireccion("direccion del lugar");
	// 	lugarRealizacion.setEmail("email@email.com");
	// 	lugarRealizacion.setId(1);
	// 	lugarRealizacion.setNombre_recinto("nombre_recinto");
	// 	lugarRealizacion.setTelefono("555666777");
	// 	lugarRealizacion.setUrlFoto("https://imagen.com");
    //     AlquilerEspacio alquilerEspacio = new AlquilerEspacio();
	// 	alquilerEspacio.setId(1);
	// 	alquilerEspacio.setPrecioTotal(2000.00);
	// 	alquilerEspacio.setLugarRealizacion(lugarRealizacion);
	// 	alquilerEspacio.setFechaInicioReserva(LocalDateTime.now().plusDays(1));
	// 	alquilerEspacio.setFechaFinReserva(alquilerEspacio.getFechaInicioReserva().plusDays(1));
    //     TipoEntrada tipoEntrada = new TipoEntrada();
    //     tipoEntrada.setFechaInicio(LocalDateTime.now().plusDays(1));
    //     tipoEntrada.setFechaFin(tipoEntrada.getFechaInicio().plusDays(1));
    //     tipoEntrada.setNombre(NombreTiposEntrada.PASE_VARIOS_DIAS);
    //     tipoEntrada.setNumEntradas(10);
    //     tipoEntrada.setPrecio(11.0);
    //     tipoEntrada.setActividades(new ArrayList<Actividad>());
    //     tipoEntrada.setId(10000);
    //     Actividad actividad = new Actividad();
    //     actividad.setDescripcionActividad("descripción de una actividad de ejemplo");
    //     actividad.setFechaInicio(LocalDateTime.now().plusDays(1));
    //     actividad.setFechaFin(actividad.getFechaInicio().plusDays(1));
    //     actividad.setId(Integer.MAX_VALUE);
    //     actividad.setTematicaActividad("tematica de una actividad");
    //     actividad.setTiposEntrada(new ArrayList<TipoEntrada>());
    //     actividad.setAlquilerEspacio(alquilerEspacio);
    //     actividadService.guardarActividad(actividad);
    //     List<Actividad> actividades = new ArrayList<>();
    //     actividades.add(actividad);
    //     tipoEntrada.setActividades(actividades);
    //     TipoEntradaService.guardar(tipoEntrada);
    //     Entrada entrada = new Entrada();
    //     entrada.setCliente(cliente);
    //     entrada.setDni("77934193G");
    //     entrada.setNombreAsistente("Persona Prueba");
    //     entrada.setId(100000);
    //     // entrada.setTipoEntrada(tipoEntrada);
    //     Integer tipoEntradaId = tipoEntrada.getId();
    //     entradaService.crearEntrada(entrada,tipoEntradaId);
    //     assertEquals(tipoEntrada, entrada.getTipoEntrada());
    // }

    
}
