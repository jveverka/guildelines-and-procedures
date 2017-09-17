package itx.backupng.server.tests;

import itx.backupng.server.cmd.tasks.GetDiskInfoTask;
import itx.backupng.server.cmd.tasks.GetSystemInfoTask;
import itx.backupng.server.cmd.tasks.TaskExecutionException;
import itx.backupng.server.controller.services.CmdExecutor;
import itx.backupng.server.model.SystemInfo;
import itx.backupng.server.model.disks.DiskInfo;
import itx.backupng.server.tests.services.MockedSingleTaskCmdExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class CmdTasksTest {

    @Test
    public void getSystemInfoTest01() throws IOException, TaskExecutionException {
        CmdExecutor cmdExecutor = new MockedSingleTaskCmdExecutor("data/system-info-reply-01-ok.txt");
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
    public void getSystemInfoTest02() throws IOException, TaskExecutionException {
        CmdExecutor cmdExecutor = new MockedSingleTaskCmdExecutor("data/system-info-reply-02-ok.txt");
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

    @Test
    public void getDiskInfo01() throws IOException, TaskExecutionException {
        CmdExecutor cmdExecutor = new MockedSingleTaskCmdExecutor("data/system-list-disks-reply-01-ok.txt");
        GetDiskInfoTask getDiskInfoTask = new GetDiskInfoTask(cmdExecutor);
        List<DiskInfo> disks = getDiskInfoTask.exec();
        Assert.assertNotNull(disks);
        Assert.assertTrue(disks.size() == 5);
    }

    @Test
    public void getDiskInfo02() throws IOException, TaskExecutionException {
        CmdExecutor cmdExecutor = new MockedSingleTaskCmdExecutor("data/system-list-disks-reply-02-ok.txt");
        GetDiskInfoTask getDiskInfoTask = new GetDiskInfoTask(cmdExecutor);
        List<DiskInfo> disks = getDiskInfoTask.exec();
        Assert.assertNotNull(disks);
        Assert.assertTrue(disks.size() == 4);
    }

    @Test
    public void getDiskInfo03() throws IOException, TaskExecutionException {
        CmdExecutor cmdExecutor = new MockedSingleTaskCmdExecutor("data/system-list-disks-reply-03-ok.txt");
        GetDiskInfoTask getDiskInfoTask = new GetDiskInfoTask(cmdExecutor);
        List<DiskInfo> disks = getDiskInfoTask.exec();
        Assert.assertNotNull(disks);
        Assert.assertTrue(disks.size() == 1);
        Assert.assertEquals(disks.get(0).getId(), "/dev/mmcblk0");
        Assert.assertTrue(disks.get(0).getPartitions().size() == 2);
    }

}
