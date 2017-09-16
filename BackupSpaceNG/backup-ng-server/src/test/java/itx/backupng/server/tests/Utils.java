package itx.backupng.server.tests;

import itx.backupng.server.cmd.CmdExecResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {

    private static final String STD_OUT = "### stdOut:";
    private static final String STD_ERR = "### stdErr:";
    private static final String EXIT_CODE = "### exitCode:";

    public static String cmdExecToString(CmdExecResult cmdExecResult) {
        StringBuffer sb = new StringBuffer();
        sb.append(STD_OUT);
        sb.append("\n");
        for (String line : cmdExecResult.getStdOut()) {
            sb.append(line);
            sb.append("\n");
        }
        sb.append(STD_ERR);
        sb.append("\n");
        for (String line : cmdExecResult.getStdErr()) {
            sb.append(line);
            sb.append("\n");
        }
        sb.append(EXIT_CODE);
        sb.append("\n");
        sb.append(cmdExecResult.getExitCode());
        return sb.toString();
    }

    public static CmdExecResult readCmdExecResult(InputStream inputStream) throws IOException {
        int status = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        List<String> stdOut = new ArrayList<>();
        List<String> stdErr = new ArrayList<>();
        int exitCode = Integer.MIN_VALUE;
        while ((line = reader.readLine()) != null) {
            if (STD_OUT.equals(line)) {
                status = 1;
                continue;
            } else if (STD_ERR.equals(line)) {
                status = 2;
                continue;
            } else if (EXIT_CODE.equals(line)) {
                status = 3;
                continue;
            }
            if (status == 1) {
                stdOut.add(line);
            }
            if (status == 2) {
                stdErr.add(line);
            }
            if (status == 3) {
                exitCode = Integer.parseInt(line);
            }
        }
        return new CmdExecResult(Collections.unmodifiableCollection(stdOut),
                Collections.unmodifiableCollection(stdErr), exitCode);
    }

}
