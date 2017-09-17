package itx.backupng.server.cmd;

import java.util.Collection;

/**
 * This class is data holder containing results of
 * command line task execution result.
 */
public class CmdExecResult {

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

}
