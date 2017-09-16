package itx.backupng.server.model;

public enum VolumeStatus {

    OFFLINE,         //BackupVolume is not visible to the system
    CONNECTED,       //BackupVolume is visible to the system, can be used
    READY,           //BackupVolume is ready to read-write data
    UNDER_INSPECTION //BackupVolume is inspected for data consistency

}
