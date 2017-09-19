package itx.backupng.server.controller;

import itx.backupng.server.config.Configuration;
import itx.backupng.server.controller.services.CmdExecutor;
import itx.backupng.server.controller.services.CmdExecutorImpl;
import itx.backupng.server.controller.services.BackupSystem;
import itx.backupng.server.controller.services.BackupSystemImpl;
import itx.backupng.server.persistence.DataService;
import itx.backupng.server.persistence.DataServiceFileImpl;
import itx.backupng.server.persistence.DataServiceInMemoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ControllerImpl implements Controller {

    final private static Logger LOG = LoggerFactory.getLogger(ControllerImpl.class);

    private Configuration configuration;
    private Services services;

    public ControllerImpl(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void start() {
        CmdExecutor cmdExecutor = new CmdExecutorImpl(configuration);
        BackupSystem system = new BackupSystemImpl(cmdExecutor);
        DataService dataService = new DataServiceInMemoryImpl();
        try {
            Path dataFilePath = Paths.get(configuration.getDataFilePath());
            if (Files.exists(dataFilePath)) {
                DataServiceFileImpl dataServiceFile = new DataServiceFileImpl(dataFilePath);
                dataService = dataServiceFile;
                LOG.info("Using persistence dataFile: {}", dataFilePath.toString());
            } else {
                LOG.info("Using in-memory data service");
            }
        } catch (Exception e) {
            LOG.info("Using in-memory data service");
        }
        this.services = new ServicesImpl(cmdExecutor, system, dataService);
    }

    @Override
    public Services getServices() {
        return services;
    }

    @Override
    public void shutdown() {
        services.getDataService().persistData();
    }

}
