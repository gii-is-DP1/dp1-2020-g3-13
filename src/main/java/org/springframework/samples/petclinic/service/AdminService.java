package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Admin;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.repository.AdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepo;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private OrganizacionService organizacionService;
    
    public int adminCount(){
        return (int) adminRepo.count();
    }

    public Iterable<Admin> findAll(){
        return adminRepo.findAll();
    }

    @Transactional(readOnly = true)
	public Admin findById(int adminId) throws DataAccessException {
		return adminRepo.findById(adminId);
    }
    
    @Transactional
    public void deleteUsuario(String usuarioId){
        Usuario u = usuarioService.findUsuario(usuarioId);
        Cliente c = clienteService.findClienteByUsuario(usuarioId);
        Organizacion o = organizacionService.encuentraOrganizacionByUsuario(usuarioId); 

            if(u.getAutoridades().getAutoridad().equals("cliente")){
                this.clienteService.deleteCliente(c);
            }
            if(u.getAutoridades().getAutoridad().equals("organizacion")){
                this.organizacionService.deleteOrganizacion(o);
            }
        
        this.usuarioService.deleteUsuario(u);
    }
}