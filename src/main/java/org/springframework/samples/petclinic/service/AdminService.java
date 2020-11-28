package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Admin;
import org.springframework.samples.petclinic.repository.AdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepo;

    @Transactional
    public int adminCount(){
        return (int) adminRepo.count();
    }

    public Iterable<Admin> findAll(){
        return adminRepo.findAll();
    }

    @Transactional
	public void saveAdmin(Admin admin) throws DataAccessException {
		adminRepo.save(admin);
    }

    @Transactional(readOnly = true)
	public Admin findById(int adminId) throws DataAccessException {
		return adminRepo.findById(adminId);
    }
    
    public void deleteAdmin(int adminId) {
        adminRepo.delete(adminRepo.findById(adminId));
    }
}