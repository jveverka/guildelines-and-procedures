package itx.backupng.server.controller.services;

import itx.backupng.server.cmd.tasks.GetDiskInfoTask;
import itx.backupng.server.cmd.tasks.TaskExecutionException;
import itx.backupng.server.model.disks.DiskInfo;

import java.util.List;

public class BackupSystemImpl implements BackupSystem {

    private CmdExecutor cmdExecutor;

    public BackupSystemImpl(CmdExecutor cmdExecutor) {
        this.cmdExecutor = cmdExecutor;
    }

    @Override
    public List<DiskInfo> getDiskInfo() throws TaskExecutionException {
        GetDiskInfoTask getDiskInfoTask = new GetDiskInfoTask(cmdExecutor);
        return getDiskInfoTask.exec();
    }

}
