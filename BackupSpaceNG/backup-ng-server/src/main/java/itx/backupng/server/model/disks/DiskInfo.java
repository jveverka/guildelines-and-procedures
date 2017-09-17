package itx.backupng.server.model.disks;

import java.util.List;

public class DiskInfo {

    private String id;
    private List<PartitionInfo> partitions;

    public DiskInfo(String id, List<PartitionInfo> partitions) {
        this.id = id;
        this.partitions = partitions;
    }

    public String getId() {
        return id;
    }

    public List<PartitionInfo> getPartitions() {
        return partitions;
    }

}
