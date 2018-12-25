package pl.matadini.sysmusic.server.context.band;

import org.junit.jupiter.api.Test;
import pl.matadini.sysmusic.server.common.test.H2Test;
import pl.matadini.sysmusic.server.context.band.dto.BandDto;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class BandServiceTest extends H2Test {

    @Test
    public void test() throws BandServiceException {


        BandService bandService = BandServiceFactory.create(entityManagerFactory);
        Long metallicaId = bandService.addBand("Metallica", LocalDate.parse("1981-04-01"));
        bandService.addBand("Megadeth", LocalDate.parse("1983-09-01"));
        bandService.addBand("Judas Priest", LocalDate.parse("1971-05-01"));

        assertEquals(3, bandService.getBands().size());

        bandService.addRecord(metallicaId, "Black Album");
        bandService.addRecord(metallicaId, "Master of Puppets");

        Optional<BandDto> bandById = bandService.getBandById(metallicaId);
        assertTrue(bandById.isPresent());

        BandDto metallicaBand = bandById.get();
        assertEquals(2, metallicaBand.getRecords().size());
    }
}