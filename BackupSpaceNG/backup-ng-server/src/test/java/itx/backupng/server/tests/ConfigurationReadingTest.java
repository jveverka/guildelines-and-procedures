package itx.backupng.server.tests;

import itx.backupng.server.config.ConfigUtils;
import itx.backupng.server.config.Configuration;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

public class ConfigurationReadingTest {

    @Test
    public void readLocalConfig() throws IOException {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("configuration-local.json");
        Configuration configuration = ConfigUtils.load(resourceAsStream);
        Assert.assertNotNull(configuration);
        Assert.assertEquals(configuration.getRootDir(), "/opt/backupng/scripts");
        Assert.assertEquals(configuration.getServerId(), "server01");
        Assert.assertNull(configuration.getRemoteSystem());
        Assert.assertNotNull(configuration.getSystemDriveIds());
        Assert.assertTrue(configuration.getSystemDriveIds().size() == 1);
        Assert.assertNotNull(configuration.getGrpcServer());
        Assert.assertNotNull(configuration.getGrpcServer().getHost());
        Assert.assertTrue(configuration.getGrpcServer().getPort() == 50051);
    }

    @Test
    public void readRemoteConfig() throws IOException {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("configuration-remote.json");
        Configuration configuration = ConfigUtils.load(resourceAsStream);
        Assert.assertNotNull(configuration);
        Assert.assertEquals(configuration.getRootDir(), "/opt/backupng/scripts");
        Assert.assertEquals(configuration.getServerId(), "server01");
        Assert.assertNotNull(configuration.getRemoteSystem());
        Assert.assertEquals(configuration.getRemoteSystem().getHost(), "192.168.30.21");
        Assert.assertEquals(configuration.getRemoteSystem().getUser(),"root");
        Assert.assertEquals(configuration.getRemoteSystem().getPassword(),"secret");
        Assert.assertTrue(configuration.getSystemDriveIds().size() == 1);
        Assert.assertNotNull(configuration.getGrpcServer());
        Assert.assertNotNull(configuration.getGrpcServer().getHost());
        Assert.assertTrue(configuration.getGrpcServer().getPort() == 50051);
    }

}
