package itx.backupng.server.cmd.tasks;

import itx.backupng.server.cmd.CmdExecResult;
import itx.backupng.server.controller.services.CmdExecutor;
import itx.backupng.server.utils.CommandUtils;
import itx.backupng.server.model.SystemInfo;

import java.io.IOException;

public class GetSystemInfoTask implements CmdTask<SystemInfo> {

    private static final String COMMAND = "system-info.sh";

    private static final String SYSTEM_NAME_KEY = "system-name";
    private static final String SYSTEM_KERNEL_VERSION_KEY = "system-kernel-version";
    private static final String SYSTEM_HOSTNAME_KEY = "system-hostname";
    private static final String SYSTEM_UPTIME_KEY = "system-uptime";

    private CmdExecutor cmdExecutor;

    public GetSystemInfoTask(CmdExecutor cmdExecutor) {
        this.cmdExecutor = cmdExecutor;
    }

    @Override
    public SystemInfo exec() throws TaskExecutionException {
        try {
            CmdExecResult execResult = cmdExecutor.exec(COMMAND);
            if (execResult.isSuccess()) {
                String hostName = "NA";
                String kernelVersion = "NA";
                String distributionName = "NA";
                Long uptime = 0l;
                for(String line: execResult.getStdOut()) {
                    line = line.trim();
                    if (line.startsWith(SYSTEM_NAME_KEY)) {
                        distributionName = CommandUtils.getValue(line);
                    } else if (line.startsWith(SYSTEM_KERNEL_VERSION_KEY)) {
                        kernelVersion = CommandUtils.getValue(line);
                    } else if (line.startsWith(SYSTEM_HOSTNAME_KEY)) {
                        hostName = CommandUtils.getValue(line);
                    } else if (line.startsWith(SYSTEM_UPTIME_KEY)) {
                        Float ut = (Float.parseFloat(CommandUtils.getValue(line))*1000);
                        uptime = ut.longValue();
                    }
                }
                return new SystemInfo(hostName, kernelVersion, distributionName, uptime);
            }
            throw new TaskExecutionException("Command exec \"" + COMMAND + "\" failed with exit code: " + execResult.getExitCode());
        } catch (IOException e) {
            throw new TaskExecutionException("IOException: ", e);
        } catch (InterruptedException e) {
            throw new TaskExecutionException("InterruptedException: ", e);
        } catch (Exception e) {
            throw new TaskExecutionException("Exception: ", e);
        }
    }

}
