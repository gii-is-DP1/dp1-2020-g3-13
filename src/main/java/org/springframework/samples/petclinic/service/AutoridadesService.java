package org.springframework.samples.petclinic.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Autoridades;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.repository.AutoridadesRepository;
import org.springframework.stereotype.Service;

@Service
public class AutoridadesService {
	private AutoridadesRepository autoridadesRepository;
	private UsuarioService usuarioService;

	@Autowired
	public AutoridadesService(AutoridadesRepository autoridadesRepository,UsuarioService usuarioService) {
		this.autoridadesRepository = autoridadesRepository;
		this.usuarioService = usuarioService;
	}

	@Transactional
	public void guardarAutoridades(Autoridades autoridades) throws DataAccessException {
		autoridadesRepository.save(autoridades);
	}
	
	@Transactional
	public void guardarAutoridades(String nombreUsuario, String autoridad) throws DataAccessException {
		Usuario usuario = usuarioService.findUsuario(nombreUsuario);
		if(usuario!=null) {
			usuario.setAutoridades(autoridadesRepository.findById(autoridad).orElse(null));
		}else
			throw new DataAccessException("User '"+nombreUsuario+" ' not found!") {};
	}



	public void deleteAutoridades(Autoridades autoridades){
		autoridadesRepository.delete(autoridades);
	}
}
