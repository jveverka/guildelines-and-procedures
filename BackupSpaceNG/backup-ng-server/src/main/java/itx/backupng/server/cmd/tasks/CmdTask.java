package itx.backupng.server.cmd.tasks;

public interface CmdTask <T> {

    /**
     * Execute command task
     * @return
     *   Data obtained by this task execution.
     * @throws TaskExecutionException
     *   Thrown in case the task execution fails or returns !=0 exit code.
     */
    T exec() throws TaskExecutionException;

}
