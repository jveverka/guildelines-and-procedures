package itx.backupng.server.controller.services;

import itx.backupng.server.cmd.tasks.TaskExecutionException;
import itx.backupng.server.model.disks.DiskInfo;

import java.util.List;

public interface BackupSystem {

    List<DiskInfo> getDiskInfo() throws TaskExecutionException;

}
