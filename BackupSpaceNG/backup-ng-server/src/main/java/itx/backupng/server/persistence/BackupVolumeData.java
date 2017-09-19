package itx.backupng.server.persistence;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BackupVolumeData {

    private String id;
    private String name;

    @JsonCreator
    public BackupVolumeData(@JsonProperty("id") String id,
                            @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
