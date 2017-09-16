package itx.backupng.server.tests;

import itx.backupng.server.cmd.CmdExecResult;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

public class CmdExecResultsTest {

    @Test
    public void testResult01() throws IOException {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("data/test/test-result-01.txt");
        CmdExecResult cmdExecResult = Utils.readCmdExecResult(resourceAsStream);
        Assert.assertNotNull(cmdExecResult);
        Assert.assertTrue(cmdExecResult.isSuccess());
        Assert.assertNotNull(cmdExecResult.getStdErr());
        Assert.assertTrue(cmdExecResult.getStdErr().size() == 0);
        Assert.assertNotNull(cmdExecResult.getStdOut());
        Assert.assertTrue(cmdExecResult.getStdOut().size() == 0);
    }

    @Test
    public void testResult02() throws IOException {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("data/test/test-result-02.txt");
        CmdExecResult cmdExecResult = Utils.readCmdExecResult(resourceAsStream);
        Assert.assertNotNull(cmdExecResult);
        Assert.assertTrue(cmdExecResult.getExitCode() == 2);
        Assert.assertNotNull(cmdExecResult.getStdErr());
        Assert.assertTrue(cmdExecResult.getStdErr().size() == 0);
        Assert.assertNotNull(cmdExecResult.getStdOut());
        Assert.assertTrue(cmdExecResult.getStdOut().size() == 2);
    }

    @Test
    public void testResult03() throws IOException {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("data/test/test-result-03.txt");
        CmdExecResult cmdExecResult = Utils.readCmdExecResult(resourceAsStream);
        Assert.assertNotNull(cmdExecResult);
        Assert.assertTrue(cmdExecResult.getExitCode() == 3);
        Assert.assertNotNull(cmdExecResult.getStdErr());
        Assert.assertTrue(cmdExecResult.getStdErr().size() == 2);
        Assert.assertNotNull(cmdExecResult.getStdOut());
        Assert.assertTrue(cmdExecResult.getStdOut().size() == 0);
    }

    @Test
    public void testResult04() throws IOException {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("data/test/test-result-04.txt");
        CmdExecResult cmdExecResult = Utils.readCmdExecResult(resourceAsStream);
        Assert.assertNotNull(cmdExecResult);
        Assert.assertTrue(cmdExecResult.getExitCode() == 4);
        Assert.assertNotNull(cmdExecResult.getStdErr());
        Assert.assertTrue(cmdExecResult.getStdErr().size() == 2);
        Assert.assertNotNull(cmdExecResult.getStdOut());
        Assert.assertTrue(cmdExecResult.getStdOut().size() == 2);
    }

}
