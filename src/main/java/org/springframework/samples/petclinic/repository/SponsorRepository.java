package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Sponsor;


public interface SponsorRepository extends CrudRepository<Sponsor, Integer> {

}