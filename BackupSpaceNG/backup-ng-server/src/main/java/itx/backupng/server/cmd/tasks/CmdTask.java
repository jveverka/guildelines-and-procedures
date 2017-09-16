package itx.backupng.server.cmd.tasks;

import itx.backupng.server.model.SystemInfo;

public interface CmdTask <T> {

    public T exec() throws TaskExecutionException;

}
