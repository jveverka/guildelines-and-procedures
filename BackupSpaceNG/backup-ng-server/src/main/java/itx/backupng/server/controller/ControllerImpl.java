package itx.backupng.server.controller;

import itx.backupng.server.config.Configuration;
import itx.backupng.server.controller.services.CmdExecutor;
import itx.backupng.server.controller.services.CmdExecutorImpl;
import itx.backupng.server.controller.services.BackupSystem;
import itx.backupng.server.controller.services.BackupSystemImpl;

public class ControllerImpl implements Controller {

    private Configuration configuration;
    private Services services;

    public ControllerImpl(Configuration configuration) {
        this.configuration = configuration;
        CmdExecutor cmdExecutor = new CmdExecutorImpl(configuration);
        BackupSystem system = new BackupSystemImpl(cmdExecutor);
        this.services = new ServicesImpl(cmdExecutor, system);
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
