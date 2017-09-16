package itx.backupng.server.controller;

import itx.backupng.server.controller.services.CmdExecutor;

public class ServicesImpl implements Services {

    private CmdExecutor cmdExecutor;

    public ServicesImpl(CmdExecutor cmdExecutor) {
        this.cmdExecutor = cmdExecutor;
    }

    @Override
    public CmdExecutor getCmdExecutor() {
        return cmdExecutor;
    }

}
