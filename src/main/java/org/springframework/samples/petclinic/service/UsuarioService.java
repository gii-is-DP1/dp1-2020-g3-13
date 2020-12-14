package org.springframework.samples.petclinic.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.repository.UsuarioRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedUsuarioNameException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private OrganizacionService organizacionService;

	@Transactional
    public int usuarioCount(){
        return (int) usuarioRepository.count();
    }
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Transactional
	public void saveUser(Usuario usuario) throws DataAccessException {
		usuario.setEnabled(true);
		usuarioRepository.save(usuario);
	}

	public Iterable<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	
	public Usuario findUsuario(String usuarioID) {
	//	return usuarioRepository.findBynombreUsuario(usuarioID);
		return usuarioRepository.findById(usuarioID).get();
	}

	public void deleteUsuario(Usuario usuario) throws DataAccessException{
		usuarioRepository.delete(usuario);
	}


	}

