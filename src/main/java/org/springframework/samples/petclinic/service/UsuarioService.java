package org.springframework.samples.petclinic.service;


import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Peticion;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;
	

    public int usuarioCount(){
        return (int) usuarioRepository.count();
    }
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Transactional
	public void saveUser(Usuario usuario) throws DataAccessException {
		usuarioRepository.save(usuario);
	}

	public Iterable<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	
	public Usuario findUsuario(String usuarioID) {
	//	return usuarioRepository.findBynombreUsuario(usuarioID);
		return usuarioRepository.findById(usuarioID).orElse(null);
	}
	@Transactional
	public void deleteUsuario(Usuario usuario) throws DataAccessException{
		usuarioRepository.delete(usuario);
	}

	public Usuario creaUsuarioParaPeticion(Peticion peticion){
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario(generaUsuario(peticion.getNombre_organizacion(), peticion.getCif()));
        usuario.setEnabled(true);
        usuario.setPassword(generaContraseña());
		return usuario;

	}

	// El metodo genera una contraseña automaticamente a partir del patrón
    // Por defecto es una contraseña de 20 caracteres pero se podría modificar
    private static String generaContraseña() {
        String password = "";
        String patron = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            password += patron.charAt(r.nextInt(patron.length()));
        }
        return password;

    }

	
    // El metodo genera un usuario automaticamente a partir del nombre de
    // organizacion
    // Se podria modificar o simplemente dar como usuario el CIF
    private static String generaUsuario(String nombreOrganizacion, String cif) {
        String res = nombreOrganizacion.replace(" ", "").toLowerCase();
        res = res.substring(0, 1).toUpperCase() + res.substring(1,7) + "-" + cif.substring(0, 5);

        return res;

    }


	}

