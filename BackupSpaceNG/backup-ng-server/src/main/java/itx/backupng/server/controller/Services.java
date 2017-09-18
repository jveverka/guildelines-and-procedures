package itx.backupng.server.controller;

import itx.backupng.server.controller.services.BackupSystem;
import itx.backupng.server.controller.services.CmdExecutor;

public interface Services {

    CmdExecutor getCmdExecutor();

    BackupSystem getSystem();

}
