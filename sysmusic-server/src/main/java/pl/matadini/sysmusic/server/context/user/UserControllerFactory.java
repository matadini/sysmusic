package pl.matadini.sysmusic.server.context.user;

import com.google.gson.GsonBuilder;
import pl.matadini.sysmusic.server.common.spark.SparkController;
import pl.matadini.sysmusic.server.common.spark.SparkControllerFactory;

import javax.persistence.EntityManagerFactory;

public class UserControllerFactory implements SparkControllerFactory {

    @Override
    public SparkController create(EntityManagerFactory entityManagerFactory) {
        return UserControllerImpl.builder()
                .entityManagerFactory(entityManagerFactory)
                .gson(new GsonBuilder().serializeNulls().create())
                .build() ;
    }
}
