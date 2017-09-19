package itx.backupng.server.model;

import java.util.Collection;

public class BackupVolume {

    private String id;
    private String name;
    private boolean master;
    private Collection<VolumeSnapshot> snapshots;

}
