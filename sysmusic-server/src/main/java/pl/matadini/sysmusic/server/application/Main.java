package pl.matadini.sysmusic.server.application;


import com.google.common.collect.Lists;
import org.pmw.tinylog.Logger;
import pl.matadini.sysmusic.server.application.configuration.Configuration;
import pl.matadini.sysmusic.server.application.configuration.ConfigurationRepository;
import pl.matadini.sysmusic.server.application.configuration.ConfigurationRepositoryFactory;
import pl.matadini.sysmusic.server.common.spark.SparkControllerFactory;
import pl.matadini.sysmusic.server.context.band.BandControllerFactory;
import pl.matadini.sysmusic.server.context.user.UserControllerFactory;
import spark.Service;

import javax.persistence.EntityManagerFactory;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        /**
         * Prepare configuration
         */
        ConfigurationRepository repository = ConfigurationRepositoryFactory.create("./config");
        Configuration configuration = repository.read();
        configuration.setJdbcDriver("org.h2.Driver");
        configuration.setJdbcUrl("jdbc:h2:mem:test");
        configuration.setJdbcUser("sa");
        configuration.setJdbcPassword("");
        configuration.setHibernateDialect("org.hibernate.dialect.H2Dialect");
        repository.save(configuration);

        /**
         * Run spark rest services
         */
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.createEntityManagerFactory(configuration);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            entityManagerFactory.close();
        }));

        SparkRunner sparkRunner = new SparkRunner();
        sparkRunner.run(entityManagerFactory);
    }


}
