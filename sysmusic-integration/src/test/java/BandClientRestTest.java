import org.junit.jupiter.api.Test;
import pl.matadini.sysmusic.client.band.SysmusicBandClient;
import pl.matadini.sysmusic.client.band.SysmusicBandClientException;
import pl.matadini.sysmusic.client.band.SysmusicBandClientFactory;
import pl.matadini.sysmusic.common.contract.band.AddBand;
import pl.matadini.sysmusic.common.contract.band.Band;
import pl.matadini.sysmusic.server.application.SparkRunner;
import pl.matadini.sysmusic.server.application.configuration.Configuration;
import pl.matadini.sysmusic.server.common.test.H2Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

public class BandClientRestTest extends H2Test {

    @Test
    public void test() throws SysmusicBandClientException {

        /**
         * Configure database
         */
        Configuration configuration = new Configuration();
        configuration.setJdbcDriver("org.h2.Driver");
        configuration.setJdbcUrl("jdbc:h2:mem:test");
        configuration.setJdbcUser("sa");
        configuration.setJdbcPassword("");
        configuration.setHibernateDialect("org.hibernate.dialect.H2Dialect");


        /**
         * Run spark service
         */
        SparkRunner sparkRunner = new SparkRunner();
        sparkRunner.run(entityManagerFactory);

        /**
         * Create client
         */
        SysmusicBandClient client = SysmusicBandClientFactory.create("http://localhost:2225");
        Long metallica = client.addBand(new AddBand("Metallica", LocalDate.parse("2010-10-10")));

        List<Band> bands = client.getBands();
        assertEquals(1, bands.size());

        Band bandById = client.getBandById(metallica);
        assertEquals("Metallica", bandById.getName());


    }
}
