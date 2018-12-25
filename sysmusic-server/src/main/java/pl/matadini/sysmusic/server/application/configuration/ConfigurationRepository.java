package pl.matadini.sysmusic.server.application.configuration;

public interface ConfigurationRepository {

    Configuration read();

    void save(Configuration configuration);

}
