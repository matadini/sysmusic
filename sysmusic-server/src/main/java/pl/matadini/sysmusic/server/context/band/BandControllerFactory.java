package pl.matadini.sysmusic.server.context.band;

import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import pl.matadini.sysmusic.server.common.spark.SparkController;
import pl.matadini.sysmusic.server.common.spark.SparkControllerFactory;

import javax.persistence.EntityManagerFactory;

public class BandControllerFactory implements SparkControllerFactory {

    @Override
    public SparkController create(EntityManagerFactory entityManagerFactory) {
        return BandControllerImpl.builder()
                .gson(new GsonBuilder().serializeNulls().create())
                .modelMapper(new ModelMapper())
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}
