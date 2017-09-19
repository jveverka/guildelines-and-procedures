package itx.backupng.server.controller;

import itx.backupng.server.controller.services.BackupSystem;
import itx.backupng.server.controller.services.CmdExecutor;
import itx.backupng.server.persistence.DataService;

public interface Services {

    CmdExecutor getCmdExecutor();

    BackupSystem getSystem();

    DataService getDataService();

}
