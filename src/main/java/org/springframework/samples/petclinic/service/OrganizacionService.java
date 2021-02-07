package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.repository.ActividadRepository;
import org.springframework.samples.petclinic.repository.OrganizacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizacionService {

    @Autowired
    private OrganizacionRepository organizacionRepo;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private ActividadRepository actividadRepo;

    @Transactional
    public int organizacionCount() {
        return (int) organizacionRepo.count();
    }

    public Iterable<Organizacion> findAll() {
        return organizacionRepo.findAll();
    }

    public Organizacion encuentraOrganizacionByUsuario(String usuario) throws DataAccessException {
        return organizacionRepo.listadoOrganizacionByUsuario(usuario);
    }

    public Boolean hayOrganizacionConEseUsuario(String usuario) throws DataAccessException {
        if (organizacionRepo.listadoOrganizacionByUsuario(usuario) != null) {
            return true;
        } else {

            return false;
        }

    }

    @Transactional
    public void deleteOrganizacion(Organizacion o) throws DataAccessException {
        for (Evento ev : eventoService.listadoEventosDeOrganizacion(o.getId())) {
            actividadRepo.deleteAll(eventoService.getActividades(ev.getId()));
            eventoService.delete(ev);
        }
        organizacionRepo.delete(o);

    }

    @Transactional
    public void saveOrganizacion(Organizacion organizacion) throws DataAccessException {
        organizacionRepo.save(organizacion);
    }

    @Transactional
    public void modifyUsuarioOrganizacion(Organizacion organizacion, Organizacion org) throws DataAccessException {
        organizacion.setId(org.getId());
        organizacion.setUsuario(org.getUsuario());
        saveOrganizacion(organizacion);
    }

    public Organizacion findOrganizacionById(int organizacionId) {
        return organizacionRepo.findById(organizacionId);
    }

    public Organizacion findOrganizacionByUsuario(String usuario) throws DataAccessException {
        return organizacionRepo.listadoOrganizacionByUsuario(usuario);
    }

    public List<Evento> getEventos(int id_organizacion) {
        return organizacionRepo.getEventos(id_organizacion);

    }

}