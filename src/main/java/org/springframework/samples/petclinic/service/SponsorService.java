package org.springframework.samples.petclinic.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Sponsor;
import org.springframework.samples.petclinic.repository.SponsorRepository;
import org.springframework.stereotype.Service;

@Service
public class SponsorService {


    @Autowired
    private SponsorRepository sponsorRepository;

    @Transactional
    public int tipoEntradaCount(){
        return (int) sponsorRepository.count();
    }

    public Iterable<Sponsor> findAll(){
        return sponsorRepository.findAll();
    }
}