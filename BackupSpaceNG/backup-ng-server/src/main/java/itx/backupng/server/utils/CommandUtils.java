package itx.backupng.server.utils;

import itx.backupng.server.cmd.CmdExecResult;
import itx.backupng.server.config.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class CommandUtils {

    private CommandUtils() {
    }

    public static Collection<String> readStream(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        List<String> result = new ArrayList<>();
        String line = "";
        do {
            line = br.readLine();
            if (line != null) {
                result.add(line);
            }
        } while (line != null);
        return Collections.unmodifiableCollection(result);
    }

    public static CmdExecResult exec(String command) throws IOException, InterruptedException {
        Runtime rt =  Runtime.getRuntime();
        Process p = rt.exec(command);
        Collection<String> stdOut = CommandUtils.readStream(p.getInputStream());
        Collection<String> stdErr = CommandUtils.readStream(p.getErrorStream());
        p.waitFor();
        return new CmdExecResult(stdOut, stdErr, p.exitValue());
    }

    public static String generateRemoteCommand(String command, Configuration configuration) {
        return "sshpass -p " + configuration.getRemoteSystem().getPassword() +
                " ssh " + configuration.getRemoteSystem().getUser() + "@" + configuration.getRemoteSystem().getHost() +
                " " + configuration.getRootDir() + "/" + command;
    }

    public static String getValue(String keyValue) {
        String[] split = keyValue.split("=");
        return split[1];
    }

}
