package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
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
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.model.TipoEvento;
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
    @Autowired
    private AlquilerEspacioService alquilerEspacioService;
    @Autowired
    private LugarRealizacionService lugarRealizacionService;
    @Autowired
    private ExponenteService exponenteService;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private OrganizacionService organizacionService;
    @Autowired
    private AutoridadesService autoridadesService;


    @Test
    public void deberiaCrearEntrada(){
        int entradasAntes = entradaService.cuentaEntradas();
        
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
        //inicializamos el evento
        Evento evento = new Evento();
        evento.setTipoEvento(TipoEvento.ACADEMICOS);
        evento.setDescripcion("descripcion del evento con una longitud apropiada para la prueba y que no fallen los validadores de la clase Evento");
        evento.setNombreEvento("nombreEvento");
        evento.setFechaFin(LocalDate.now());
        evento.setFechaInicio(LocalDate.now());
        evento.setEsPublico(true);
        evento.setOrganizacion(organizacion);
        eventoService.save(evento);
        //inicializamos el exponente
        Exponente exponente = new Exponente();
        exponente.setNombreExponente("Exponente");
        exponente.setApellidosExponente("De Prueba");
        exponente.setAlias("Test 1");
        exponenteService.guardarExponente(exponente);
        List<Exponente> exponentes = new ArrayList<>();
        exponentes.add(exponente);
        //inicializamos el lugar Realizacion
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
		// alquilerEspacio.setId(1);
		alquilerEspacio.setPrecioTotal(2000.00);
		alquilerEspacio.setLugarRealizacion(lugarRealizacion);
		alquilerEspacio.setFechaInicioReserva(LocalDateTime.now().plusDays(1));
		alquilerEspacio.setFechaFinReserva(alquilerEspacio.getFechaInicioReserva().plusDays(1));
        alquilerEspacioService.guardarAlquilerEspacio(alquilerEspacio);
        //inicializamos la actividad
        Actividad actividad = new Actividad();
		actividad.setDescripcionActividad("descripcion cualquiera");
		actividad.setAlquilerEspacio(alquilerEspacio);
		actividad.setFechaInicio(LocalDateTime.now().plusDays(1).plusHours(1));
		actividad.setFechaFin(actividad.getFechaInicio().plusDays(1));
		actividad.setTematicaActividad("tematicaActividad");
        actividadService.guardarActividad(actividad);
        List<Actividad> actividades = new ArrayList<>();
        actividades.add(actividad);
        //inicializamos el tipo de entrada
        TipoEntrada tipoEntrada = new TipoEntrada();
        tipoEntrada.setFechaInicio(LocalDateTime.now().plusDays(1));
        tipoEntrada.setFechaFin(tipoEntrada.getFechaInicio().plusDays(1));
        tipoEntrada.setId(Integer.MAX_VALUE);
        tipoEntrada.setNombre(NombreTiposEntrada.PASE_VARIOS_DIAS);
        tipoEntrada.setPrecio(12.0);
        tipoEntrada.setEvento(evento);
        tipoEntrada.setNumEntradas(300);
        tipoEntrada.setActividades(actividades);
        TipoEntradaService.guardar(tipoEntrada);
        //creamos el cliente
        Usuario usuario1 = new Usuario();
        usuario.setEnabled(true);
        usuario.setNombreUsuario("prueba");
        usuario.setPassword("Password8");

        Autoridades autoridades1 = new Autoridades();
        autoridades1.setAutoridad("cliente");
        usuario1.setAutoridades(autoridades1);
        usuarioService.saveUser(usuario1);
        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario1);
        cliente.setApellidos("prueba");
        cliente.setEmail("prueba@gmail.com");
        cliente.setNombre("prueba");
        cliente.setTelefono("568748459");
        clienteService.saveCliente(cliente);
        //inicializamos la entrada
        Entrada entrada = new Entrada();
        entrada.setDni("77934193G");
        entrada.setNombreAsistente("Mercedes Benz Coche");
        entradaService.crearEntrada(entrada, tipoEntrada.getId(), cliente);
        int entradasAhora = entradaService.cuentaEntradas();
        assertEquals(entradasAntes+1, entradasAhora);

    }
    
}
