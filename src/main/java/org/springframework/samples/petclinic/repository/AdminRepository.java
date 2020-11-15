package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer>{
    
}
