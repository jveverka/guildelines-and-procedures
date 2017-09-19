package itx.backupng.server.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class DataServiceFileImpl implements DataService {

    final private static Logger LOG = LoggerFactory.getLogger(DataServiceFileImpl.class);

    private Path dataFilePath;
    private PersistentData persistentData;
    private ObjectMapper objectMapper;

    public DataServiceFileImpl(Path dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    public void init() throws IOException {
        LOG.info("init ...");
        if (Files.exists(dataFilePath)) {
            InputStream is = Files.newInputStream(dataFilePath);
            this.objectMapper = new ObjectMapper();
            this.persistentData = objectMapper.readValue(is, PersistentData.class);
            LOG.info("data loaded from persistnece file");
        } else {
            LOG.info("persistence file does not exist, creating one.");
            this.persistentData = new PersistentData(new ArrayList<>());
            persistData();
        }
    }

    @Override
    public PersistentData getData() {
        return persistentData;
    }

    @Override
    public void persistData() {
        try {
            LOG.info("persisting data");
            OutputStream outputStream = Files.newOutputStream(dataFilePath);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputStream, persistentData);
        } catch (IOException e) {
            LOG.info("IOException: ", e);
        }
    }

}
