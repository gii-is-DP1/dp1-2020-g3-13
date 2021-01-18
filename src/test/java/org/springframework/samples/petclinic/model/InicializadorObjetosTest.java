package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

//Objetos para los test, todos los objetos están creados y cumpliendo las restricciones de los validadores
public class InicializadorObjetosTest {

    public static Evento eventoParaTest() {
        Evento evento = new Evento();
        evento.setCategoria("categoria");
        evento.setDescripcion("descripcion de un evento cualquiera");
        evento.setFechaInicio(LocalDate.now());
        evento.setFechaFin(LocalDate.now().plusDays(1));
        evento.setId(Integer.MAX_VALUE);
        evento.setMedidasSanitarias("6M");
        evento.setNombreEvento("nombreEvento");
        evento.setTipoEvento("tipoEvento");
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
        return tipoEntrada;

    }

    public Consulta consultaParaTest() {
        Consulta consulta = new Consulta();
        consulta.setAsunto("asunto");
        consulta.setDescripcion("esta es la descripcion");
        return consulta;

    }

}
