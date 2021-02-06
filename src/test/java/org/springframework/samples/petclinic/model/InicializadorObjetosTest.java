package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.samples.petclinic.service.OrganizacionService;

//Objetos para los test, todos los objetos están creados y cumpliendo las restricciones de los validadores
public class InicializadorObjetosTest {
    
    OrganizacionService organizacionService;

    public static Evento eventoParaTest() {
        Evento evento = new Evento();
        evento.setCategoria("categoria");
        evento.setDescripcion("descripcion de un evento cualquiera");
        evento.setFechaInicio(LocalDate.now());
        evento.setFechaFin(LocalDate.now().plusDays(1));
        evento.setId(Integer.MAX_VALUE);
        evento.setMedidasSanitarias("6M");
        evento.setNombreEvento("nombreEvento");
        evento.setTipoEvento(TipoEvento.ACADEMICOS);
        evento.setTipoEntradas(new ArrayList<TipoEntrada>());
        evento.setActividades(new ArrayList<Actividad>());
        evento.setConsultas(new ArrayList<Consulta>());
        evento.setSponsors(new ArrayList<Sponsor>());
        return evento;
    }

    public static TipoEntrada tipoEntradaParaTest() {
        TipoEntrada tipoEntrada = new TipoEntrada();
        tipoEntrada.setFechaInicio(LocalDateTime.now().plusDays(1));
        tipoEntrada.setFechaFin(tipoEntrada.getFechaInicio().plusDays(1));
        tipoEntrada.setNombre(NombreTiposEntrada.PASE_VARIOS_DIAS);
        tipoEntrada.setNumEntradas(10);
        tipoEntrada.setPrecio(11.0);
        tipoEntrada.setActividades(new ArrayList<Actividad>());
        tipoEntrada.setId(Integer.MAX_VALUE);
        return tipoEntrada;

    }

    public static Consulta consultaParaTest() {
        Consulta consulta = new Consulta();
        consulta.setAsunto("asunto2");
        consulta.setDescripcion("esta es la segunda descripcion");
        return consulta;

    }

    public static Actividad actividadParaTest(){
        Actividad actividad = new Actividad();
        actividad.setDescripcionActividad("descripción de una actividad de ejemplo");
        actividad.setFechaInicio(LocalDateTime.now().plusDays(1));
        actividad.setFechaFin(actividad.getFechaInicio().plusDays(1));
        actividad.setId(Integer.MAX_VALUE);
        actividad.setTematicaActividad("tematica de una actividad");
        // actividad.setExponentes( new ArrayList<Exponente>());
        //actividad.setTiposEntrada(new ArrayList<TipoEntrada>());
        return actividad;
    }
        

    public static Exponente exponenteParaTest(){
        Exponente exponente = new Exponente();
        exponente.setAlias("alias");
        exponente.setNombreExponente("nombreExponente");
        exponente.setApellidosExponente("apellidosExponente");
        exponente.setId(Integer.MAX_VALUE);
        exponente.setActividades(new ArrayList<Actividad>());
        return exponente;
    }
    
    public static Usuario usuarioParaTest(){
        Usuario usuario = new Usuario();
        usuario.setPassword("password");
        usuario.setNombreUsuario("nombreUsuario");
        usuario.setFacturas(new ArrayList<>());
        usuario.setEnabled(true);
        usuario.setAutoridades(new Autoridades());
        return usuario;
    }
    public static Cliente clienteParaTest(){
        Cliente cliente = new Cliente();
        cliente.setApellidos("apellidos");
        cliente.setEmail("email@email.com");
        cliente.setEntradas(new ArrayList<Entrada>());
        cliente.setEventosFavoritos(new ArrayList<Evento>());
        cliente.setId(Integer.MAX_VALUE);
        cliente.setNombre("nombre");
        cliente.setTelefono("767656545");
        Usuario usuario = new Usuario();
        cliente.setUsuario(usuarioParaTest());
        return cliente;

    }
    public static Entrada entradaParaTest(){
        Entrada entrada = new Entrada();
        entrada.setCliente(clienteParaTest());
        entrada.setDni("00000000L");
        entrada.setNombreAsistente("Persona Prueba");
        entrada.setId(Integer.MAX_VALUE);
        entrada.setTipoEntrada(tipoEntradaParaTest());
        return entrada;
    }
    }