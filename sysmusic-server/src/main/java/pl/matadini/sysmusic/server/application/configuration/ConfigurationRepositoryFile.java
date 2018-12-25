package pl.matadini.sysmusic.server.application.configuration;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.Builder;
import org.pmw.tinylog.Logger;

import java.io.*;
import java.nio.file.Files;

@Builder
class ConfigurationRepositoryFile implements ConfigurationRepository {

    Gson gson;
    String filePath;

    @Override
    public Configuration read() {

        File file = new File(filePath);
        if (!file.exists()) {
            this.save(Configuration.empty());
            return this.read();
        } else {

            try(FileReader reader = new FileReader(filePath)) {

                StringBuffer buffer = new StringBuffer();
                int content;
                while ((content = reader.read()) != -1) {
                    buffer.append((char)content);
                }

                String json = buffer.toString();
                return gson.fromJson(json, Configuration.class);

            } catch (Exception ex) {
                Logger.error(ex);
            }
        }

        return Configuration.empty();
    }

    @Override
    public void save(Configuration configuration) {

        String json = gson.toJson(configuration);
        try (PrintWriter stream = new PrintWriter(filePath)) {
            stream.write(json);
        } catch (Exception ex) {
            Logger.error(ex);
        }
    }
}
