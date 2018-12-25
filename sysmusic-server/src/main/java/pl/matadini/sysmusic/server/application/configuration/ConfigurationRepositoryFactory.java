package pl.matadini.sysmusic.server.application.configuration;

import com.google.gson.GsonBuilder;

public class ConfigurationRepositoryFactory {

    public static ConfigurationRepository create(String file) {
        return ConfigurationRepositoryFile.builder()
                .filePath(file)
                .gson(new GsonBuilder().serializeNulls().create())
                .build();
    }

    private ConfigurationRepositoryFactory() {

    }
}
