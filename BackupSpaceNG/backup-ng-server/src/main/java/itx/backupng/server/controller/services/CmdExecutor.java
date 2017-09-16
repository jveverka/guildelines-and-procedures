package itx.backupng.server.controller.services;

import itx.backupng.server.cmd.CmdExecResult;

import java.io.IOException;

public interface CmdExecutor {

    CmdExecResult exec(String command) throws IOException, InterruptedException;

}
