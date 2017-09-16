package itx.backupng.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ConfigUtils {

    private ConfigUtils() {
    }

    public static Configuration load(InputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(inputStream, Configuration.class);
    }

    public static Configuration load(Path filePath) throws IOException {
        return load(Files.newInputStream(filePath));
    }

}