package itx.backupng.server.cmd;

import itx.backupng.server.utils.CommandUtils;

import java.util.Collection;
import java.util.concurrent.Callable;

public class CommandExecTask implements Callable<CmdExecResult> {

    private String command;

    public CommandExecTask(String command) {
        this.command =command;
    }

    @Override
    public CmdExecResult call() throws Exception {
        Runtime rt =  Runtime.getRuntime();
        Process p = rt.exec(command);
        Collection<String> stdOut = CommandUtils.readStream(p.getInputStream());
        Collection<String> stdErr = CommandUtils.readStream(p.getErrorStream());
        p.waitFor();
        return new CmdExecResult(stdOut, stdErr, p.exitValue());
    }

}
