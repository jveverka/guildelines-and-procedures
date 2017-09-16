package itx.backupng.server.tests.services;

import itx.backupng.server.cmd.CmdExecResult;
import itx.backupng.server.controller.services.CmdExecutor;
import itx.backupng.server.tests.Utils;

import java.io.IOException;
import java.io.InputStream;

public class MockedSingleTaskCmdExecutor implements CmdExecutor {

    private CmdExecResult cmdExecResult;

    public MockedSingleTaskCmdExecutor(String cmdDataPath) throws IOException {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(cmdDataPath);
        cmdExecResult = Utils.readCmdExecResult(resourceAsStream);
    }

    @Override
    public CmdExecResult exec(String command) throws IOException, InterruptedException {
        return cmdExecResult;
    }

}
