package org.springframework.samples.petclinic.formatters;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.LugarRealizacion;
import org.springframework.samples.petclinic.repository.LugarRealizacionRepository;
import org.springframework.samples.petclinic.service.LugarRealizacionService;
import org.springframework.stereotype.Component;

@Component
public class lugarRealizacionFormatter {

    private final LugarRealizacionRepository lugarRepo;

    private final LugarRealizacionService lugarService;

	@Autowired
	public lugarRealizacionFormatter(LugarRealizacionRepository lugarRepo, LugarRealizacionService lugarService) {
        this.lugarRepo = lugarRepo;
        this.lugarService = lugarService;
	}

	
	public String print(LugarRealizacion lugar, Locale locale) {
		return lugar.getNombre_recinto();
	}


	public LugarRealizacion parse(String text, Locale locale) throws ParseException {
		Iterable<LugarRealizacion> encuentraLugares = this.lugarService.findAll();
		for (LugarRealizacion lugar : encuentraLugares) {
			if (lugar.getNombre_recinto().equals(text)) {
				return lugar;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}
}
