package itx.backupng.server.model.disks;

public class PartitionInfo {

    private String id;
    private String uuid;

    public PartitionInfo(String id, String uuid) {
        this.id = id;
        this.uuid = uuid;
    }

    public String getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

}
