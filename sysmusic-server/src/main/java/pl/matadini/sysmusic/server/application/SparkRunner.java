package pl.matadini.sysmusic.server.application;

import com.google.common.collect.Lists;
import pl.matadini.sysmusic.server.application.configuration.Configuration;
import pl.matadini.sysmusic.server.common.spark.SparkControllerFactory;
import pl.matadini.sysmusic.server.context.band.BandControllerFactory;
import pl.matadini.sysmusic.server.context.user.UserControllerFactory;
import spark.Service;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class SparkRunner {


    public void run(EntityManagerFactory entityManagerFactory) {
        Service endpoint = Service.ignite().port(2225);

        List<SparkControllerFactory> factories = Lists.newArrayList();
        factories.add(new UserControllerFactory());
        factories.add(new BandControllerFactory());



        for (SparkControllerFactory item : factories) {
            item.create(entityManagerFactory).initialize(endpoint);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            endpoint.stop();
        }));
    }
}
