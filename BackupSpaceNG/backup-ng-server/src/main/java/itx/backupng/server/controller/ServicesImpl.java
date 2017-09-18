package itx.backupng.server.controller;

import itx.backupng.server.controller.services.CmdExecutor;
import itx.backupng.server.controller.services.BackupSystem;

public class ServicesImpl implements Services {

    private CmdExecutor cmdExecutor;
    private BackupSystem backupSystem;

    public ServicesImpl(CmdExecutor cmdExecutor, BackupSystem backupSystem) {
        this.cmdExecutor = cmdExecutor;
        this.backupSystem = backupSystem;
    }

    @Override
    public CmdExecutor getCmdExecutor() {
        return cmdExecutor;
    }

    @Override
    public BackupSystem getSystem() {
        return backupSystem;
    }

}
