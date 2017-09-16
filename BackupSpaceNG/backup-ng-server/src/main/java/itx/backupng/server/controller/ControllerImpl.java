package itx.backupng.server.controller;

import itx.backupng.server.config.Configuration;
import itx.backupng.server.controller.services.CmdExecutor;
import itx.backupng.server.controller.services.CmdExecutorImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ControllerImpl implements Controller {

    private Configuration configuration;
    private Services services;

    public ControllerImpl(Configuration configuration) {
        this.configuration = configuration;
        CmdExecutor cmdExecutor = new CmdExecutorImpl(configuration);
        this.services = new ServicesImpl(cmdExecutor);
    }

    @Override
    public void start() {

    }

    @Override
    public Services getServices() {
        return services;
    }

    @Override
    public void shutdown() {

    }

}
