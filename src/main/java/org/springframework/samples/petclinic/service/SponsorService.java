package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Sponsor;
import org.springframework.samples.petclinic.repository.SponsorRepository;
import org.springframework.stereotype.Service;

@Service
public class SponsorService {


    @Autowired
    private SponsorRepository sponsorRepository;

    @Transactional
    public int sponsorCount(){
        return (int) sponsorRepository.count();
    }

    public Iterable<Sponsor> findAll(){
        return sponsorRepository.findAll();
    }

    @Transactional
    public void anadirSponsorAEvento(Evento evento, Sponsor sponsor) throws DataAccessException{
        if(evento.getSponsors()==null){
            List<Sponsor> listaSponsor = new ArrayList<Sponsor>();
            sponsor.setEvento(evento);
            listaSponsor.add(sponsor);
            evento.setSponsors(listaSponsor);
        }else{
            List<Sponsor> listaSponsorsActual = evento.getSponsors();
            sponsor.setEvento(evento);
            listaSponsorsActual.add(sponsor);
        }
    }

	public void guardarSponsor(@Valid Sponsor sponsor) {
        sponsorRepository.save(sponsor);
	}
}