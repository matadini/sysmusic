package pl.matadini.sysmusic.server.common.spark;


import javax.persistence.EntityManagerFactory;

public interface SparkControllerFactory {
    SparkController create(EntityManagerFactory entityManagerFactory);
}
