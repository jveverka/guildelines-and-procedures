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

    public static CmdExecResult readCmdExecResult(InputStream inputStream) throws IOException {
        int status = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        List<String> stdOut = new ArrayList<>();
        List<String> stdErr = new ArrayList<>();
        int exitCode = Integer.MIN_VALUE;
        while ((line = reader.readLine()) != null) {
            if (CmdExecResult.STD_OUT_DELIMITER.equals(line)) {
                status = 1;
                continue;
            } else if (CmdExecResult.STD_ERR_DELIMITER.equals(line)) {
                status = 2;
                continue;
            } else if (CmdExecResult.EXIT_CODE_DELIMITER.equals(line)) {
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
