package itx.backupng.server.controller.services;

import itx.backupng.server.cmd.CmdExecResult;
import itx.backupng.server.config.Configuration;
import itx.backupng.server.utils.CommandUtils;

import java.io.IOException;

public class CmdExecutorImpl implements CmdExecutor {

    private Configuration configuration;

    public CmdExecutorImpl(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public CmdExecResult exec(String command) throws IOException, InterruptedException {
        if (configuration.useRemoteCalls()) {
            command = CommandUtils.generateRemoteCommand(command, configuration);
        } else {
            command = configuration.getRootDir() + "/" + command;
        }
        return CommandUtils.exec(command);
    }

}
