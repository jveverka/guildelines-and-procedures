package itx.backupng.server.cmd;

import java.util.Collection;

/**
 * This class is data holder containing results of
 * command line task execution result.
 */
public class CmdExecResult {

    public static final String STD_OUT_DELIMITER = "### stdOut:";
    public static final String STD_ERR_DELIMITER = "### stdErr:";
    public static final String EXIT_CODE_DELIMITER = "### exitCode:";

    private Collection<String> stdOut;
    private Collection<String> stdErr;
    private int exitCode;

    public CmdExecResult(Collection<String> stdOut, Collection<String> stdErr, int exitCode) {
        this.stdOut = stdOut;
        this.stdErr = stdErr;
        this.exitCode = exitCode;
    }

    /**
     * returns standard output from the task as collection of lines.
     * @return
     */
    public Collection<String> getStdOut() {
        return stdOut;
    }

    /**
     * returns error output from the task as collection of lines.
     * @return
     */
    public Collection<String> getStdErr() {
        return stdErr;
    }

    /**
     * returns exit code of executed task
     * @return
     */
    public int getExitCode() {
        return exitCode;
    }

    /**
     * returns if task was executed successfully, if exitCode == 0
     * @return
     */
    public boolean isSuccess() {
        return exitCode == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmdExecResult that = (CmdExecResult) o;

        if (exitCode != that.exitCode) return false;
        if (!stdOut.equals(that.stdOut)) return false;
        return stdErr.equals(that.stdErr);
    }

    @Override
    public int hashCode() {
        int result = stdOut.hashCode();
        result = 31 * result + stdErr.hashCode();
        result = 31 * result + exitCode;
        return result;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(STD_OUT_DELIMITER);
        sb.append("\n");
        for (String line : stdOut) {
            sb.append(line);
            sb.append("\n");
        }
        sb.append(STD_ERR_DELIMITER);
        sb.append("\n");
        for (String line : stdErr) {
            sb.append(line);
            sb.append("\n");
        }
        sb.append(EXIT_CODE_DELIMITER);
        sb.append("\n");
        sb.append(exitCode);
        return sb.toString();
    }

}
