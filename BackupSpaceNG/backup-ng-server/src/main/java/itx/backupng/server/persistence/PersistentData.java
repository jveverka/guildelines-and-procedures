package itx.backupng.server.persistence;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PersistentData {

    private List<BackupVolumeData> backupVolumes;

    @JsonCreator
    protected PersistentData(@JsonProperty("backupVolumes") List<BackupVolumeData> backupVolumes) {
        this.backupVolumes = backupVolumes;
    }

    public void removeBackupVolume(String id) {
        this.backupVolumes = backupVolumes.stream().filter(b -> !b.getId().equals(id)).collect(Collectors.toList());
    }

    public void addBackupVolume(BackupVolumeData backupVolumeData) {
        backupVolumes.add(backupVolumeData);
    }

    public List<BackupVolumeData> getBackupVolumes() {
        return Collections.unmodifiableList(backupVolumes);
    }

}
