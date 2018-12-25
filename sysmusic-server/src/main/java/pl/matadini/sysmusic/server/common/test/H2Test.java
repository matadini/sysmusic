package pl.matadini.sysmusic.server.common.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pl.matadini.sysmusic.server.application.EntityManagerFactoryProvider;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class H2Test {

    protected EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void beforeAll() {
        entityManagerFactory = EntityManagerFactoryProvider.createEntityManagerFactoryH2();
    }

    @AfterEach
    public void afterAll() {
        entityManagerFactory.close();
    }

}
