package itx.backupng.server.controller;

import itx.backupng.server.controller.services.CmdExecutor;
import itx.backupng.server.controller.services.BackupSystem;
import itx.backupng.server.persistence.DataService;

public class ServicesImpl implements Services {

    private CmdExecutor cmdExecutor;
    private BackupSystem backupSystem;
    private DataService dataService;

    public ServicesImpl(CmdExecutor cmdExecutor, BackupSystem backupSystem, DataService dataService) {
        this.cmdExecutor = cmdExecutor;
        this.backupSystem = backupSystem;
        this.dataService = dataService;
    }

    @Override
    public CmdExecutor getCmdExecutor() {
        return cmdExecutor;
    }

    @Override
    public BackupSystem getSystem() {
        return backupSystem;
    }

    @Override
    public DataService getDataService() {
        return dataService;
    }

}
