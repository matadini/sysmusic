package pl.matadini.sysmusic.server.integration;

import org.junit.jupiter.api.Test;
import pl.matadini.sysmusic.server.common.test.H2Test;
import pl.matadini.sysmusic.server.context.band.BandService;
import pl.matadini.sysmusic.server.context.band.BandServiceException;
import pl.matadini.sysmusic.server.context.band.BandServiceFactory;
import pl.matadini.sysmusic.server.context.publisher.PublisherService;
import pl.matadini.sysmusic.server.context.publisher.PublisherServiceException;
import pl.matadini.sysmusic.server.context.publisher.PublisherServiceFactory;
import pl.matadini.sysmusic.server.context.publisher.dto.PublisherAddressDto;

import java.time.LocalDate;

public class BandPublisherIntegrationTest extends H2Test {

    @Test
    public void test() throws BandServiceException, PublisherServiceException {

        /**
         * Band
         */
        BandService bandService = BandServiceFactory.create(entityManagerFactory);
        Long metallicaId = bandService.addBand("Metallica", LocalDate.parse("1981-01-01"));

        Long masterOfPuppetsId = bandService.addRecord(metallicaId, "Master of Puppets");

        /**
         * Publisher
         */
        PublisherAddressDto publisherAddress = new PublisherAddressDto();
        publisherAddress.setCity("Lublin");
        publisherAddress.setStreet("King Street");
        publisherAddress.setPostcode("00-000");
        publisherAddress.setApartamentNumber("10");

        PublisherService publisherService = PublisherServiceFactory.create(entityManagerFactory);
        Long sonyId = publisherService.addPublisher("Sony", publisherAddress);
        publisherService.addRecord(sonyId, masterOfPuppetsId);


    }
}
