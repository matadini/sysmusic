package pl.matadini.sysmusic.server.context.publisher;

import org.junit.jupiter.api.Test;
import pl.matadini.sysmusic.server.common.test.H2Test;
import pl.matadini.sysmusic.server.context.publisher.dto.PublisherAddressDto;
import pl.matadini.sysmusic.server.context.publisher.dto.PublisherDto;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class PublisherServiceTest extends H2Test {

    @Test
    public void test() throws PublisherServiceException {

        PublisherAddressDto build = new PublisherAddressDto();
        build.setPostcode("00-000");
        build.setCity("Warszawa");
        build.setStreet("Radziwilowska");
        build.setApartamentNumber("23/43");

        PublisherService publisherService = PublisherServiceFactory.create(entityManagerFactory);
        Long sonyMusic = publisherService.addPublisher("Sony Music", build);

        assertEquals(1, publisherService.getPublishers().size());

        PublisherDto sonyDto = publisherService.getPublisher(sonyMusic);
        org.pmw.tinylog.Logger.info(sonyDto);


    }


}