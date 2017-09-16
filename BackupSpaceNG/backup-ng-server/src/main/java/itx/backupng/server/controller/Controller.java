package itx.backupng.server.controller;

public interface Controller {

    void start();

    Services getServices();

    void shutdown();

}
