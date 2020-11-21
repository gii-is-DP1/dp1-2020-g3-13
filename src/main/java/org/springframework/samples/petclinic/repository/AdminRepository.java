package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer>{

//    @Query("SELECT admin FROM Admins admin WHERE admin.id =:id")
	public Admin findById(int id);
/*
    @Query("SELECT DISTINCT admin FROM ADMINS WHERE admin.nombre LIKE :nombre%")
    public Collection<Admin> findByName(@Param("nombre") String nombre);
*/
}
