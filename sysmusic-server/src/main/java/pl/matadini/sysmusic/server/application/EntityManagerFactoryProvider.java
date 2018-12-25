package pl.matadini.sysmusic.server.application;

import com.google.common.collect.Maps;
import pl.matadini.sysmusic.server.application.configuration.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Map;

public class EntityManagerFactoryProvider {

    public static EntityManagerFactory createEntityManagerFactory(Configuration configuration) {
        Map<String, String> properties = Maps.newHashMap();
        properties.put("javax.persistence.jdbc.driver", configuration.getJdbcDriver());
        properties.put("javax.persistence.jdbc.url", configuration.getJdbcUrl());
        properties.put("javax.persistence.jdbc.user", configuration.getJdbcUser());
        properties.put("javax.persistence.jdbc.password", configuration.getJdbcPassword());
        properties.put("show_sql", "true");
        properties.put("hibernate.dialect", configuration.getHibernateDialect());
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
        return Persistence.createEntityManagerFactory("H2PU", properties);
    }

    public static EntityManagerFactory createEntityManagerFactoryH2() {
        Map<String, String> properties = Maps.newHashMap();
        properties.put("javax.persistence.jdbc.driver", "org.h2.Driver");
        properties.put("javax.persistence.jdbc.url", "jdbc:h2:mem:test");
        properties.put("javax.persistence.jdbc.user", "sa");
        properties.put("javax.persistence.jdbc.password", "");
        properties.put("show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
        return Persistence.createEntityManagerFactory("H2PU", properties);
    }

    private EntityManagerFactoryProvider() {

    }
}
