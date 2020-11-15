package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
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
}
