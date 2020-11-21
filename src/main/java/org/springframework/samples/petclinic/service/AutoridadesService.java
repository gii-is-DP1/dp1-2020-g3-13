package org.springframework.samples.petclinic.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Autoridades;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.repository.AutoridadesRepository;

public class AutoridadesService {
	private AutoridadesRepository autoridadesRepository;
	private UsuarioService usuarioService;

	@Autowired
	public AutoridadesService(AutoridadesRepository autoridadesRepository,UsuarioService usuarioService) {
		this.autoridadesRepository = autoridadesRepository;
		this.usuarioService = usuarioService;
	}

	@Transactional
	public void saveAuthorities(Autoridades autoridades) throws DataAccessException {
		autoridadesRepository.save(autoridades);
	}
	
	@Transactional
	public void saveAuthorities(String nombreUsuario, String role) throws DataAccessException {
		Autoridades autoridades = new Autoridades();
		Optional<Usuario> usuario = usuarioService.findUsuario(nombreUsuario);
		if(usuario.isPresent()) {
			autoridades.setUsuario(usuario.get());
			autoridades.setAutoridad(role);
			//user.get().getAuthorities().add(authority);
			autoridadesRepository.save(autoridades);
		}else
			throw new DataAccessException("User '"+nombreUsuario+"' not found!") {};
	}


}
