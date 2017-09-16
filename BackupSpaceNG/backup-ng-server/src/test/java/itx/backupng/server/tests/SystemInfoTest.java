package itx.backupng.server.tests;

import itx.backupng.server.cmd.tasks.GetSystemInfoTask;
import itx.backupng.server.cmd.tasks.TaskExecutionException;
import itx.backupng.server.controller.services.CmdExecutor;
import itx.backupng.server.model.SystemInfo;
import itx.backupng.server.tests.services.MockedSingleTaskCmdExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SystemInfoTest {

    @Test
    public void getSystemInfoTestOk() throws IOException, TaskExecutionException {
        CmdExecutor cmdExecutor = new MockedSingleTaskCmdExecutor("data/system-info-reply-ok.txt");
        GetSystemInfoTask getSystemInfoTask = new GetSystemInfoTask(cmdExecutor);
        SystemInfo systemInfo = getSystemInfoTask.exec();
        Assert.assertNotNull(systemInfo);
        Assert.assertNotNull(systemInfo.getDistributionName());
        Assert.assertNotNull(systemInfo.getHostName());
        Assert.assertNotNull(systemInfo.getKernelVersion());
        Assert.assertNotNull(systemInfo.getUptime());
        Assert.assertTrue(systemInfo.getUptime() > 0);
    }

    @Test
    public void getSystemInfoTestError() throws IOException {
        CmdExecutor cmdExecutor = new MockedSingleTaskCmdExecutor("data/system-info-reply-err.txt");
        GetSystemInfoTask getSystemInfoTask = new GetSystemInfoTask(cmdExecutor);
        try {
            getSystemInfoTask.exec();
            Assert.fail();
        } catch (TaskExecutionException e) {
        }
    }

}
