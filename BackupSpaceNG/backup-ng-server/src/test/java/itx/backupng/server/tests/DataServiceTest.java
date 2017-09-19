package itx.backupng.server.tests;

import itx.backupng.server.persistence.BackupVolumeData;
import itx.backupng.server.persistence.DataServiceFileImpl;
import itx.backupng.server.persistence.PersistentData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DataServiceTest {

    @Test
    public void testDataService() throws IOException {
        Path tempFilePath = Files.createTempFile("tempfiles-", ".tmp");
        String defaultContent = "{ \"backupVolumes\":[] }";
        Files.write(tempFilePath, defaultContent.getBytes(Charset.forName("UTF-8")));
        DataServiceFileImpl dataService01 = new DataServiceFileImpl(tempFilePath);
        dataService01.init();
        PersistentData data01 = dataService01.getData();
        BackupVolumeData backupVolumeData01 = new BackupVolumeData("bv01", "backupVolume01");
        BackupVolumeData backupVolumeData02 = new BackupVolumeData("bv02", "backupVolume02");
        data01.addBackupVolume(backupVolumeData01);
        data01.addBackupVolume(backupVolumeData02);
        dataService01.persistData();
        DataServiceFileImpl dataService02 = new DataServiceFileImpl(tempFilePath);
        dataService02.init();
        PersistentData data02 = dataService01.getData();
        List<BackupVolumeData> backupVolumes = data02.getBackupVolumes();
        Assert.assertTrue(backupVolumes.size() == 2);
        Files.delete(tempFilePath);
    }

}
