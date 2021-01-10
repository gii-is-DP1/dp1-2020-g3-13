package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Sponsor;
import org.springframework.samples.petclinic.repository.SponsorRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class SponsorServiceTest {
    @Autowired
    private SponsorService sponsorService;

    @Test
    public void deberiaAñadirSponsor(){



        int count = sponsorService.sponsorCount();
        Sponsor sponsor = new Sponsor();
        sponsor.setEmpresa("Mercedes");
        sponsor.setUrlWeb("https://www.mercedes-benz.com/en/");
        sponsor.setUrlLogo("https://lezebre.lu/images/detailed/17/30356-Mercedes-Benz.png");
        sponsorService.guardarSponsor(sponsor);
        assertEquals(count+1, sponsorService.sponsorCount());
    }
    
    
}

