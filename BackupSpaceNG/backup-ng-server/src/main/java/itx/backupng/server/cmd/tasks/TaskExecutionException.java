package itx.backupng.server.cmd.tasks;

public class TaskExecutionException extends Exception {

    public TaskExecutionException(String message) {
        super(message);
    }

    public TaskExecutionException(Exception e) {
        super(e);
    }

    public TaskExecutionException(String message, Exception e) {
        super(message, e);
    }

}
