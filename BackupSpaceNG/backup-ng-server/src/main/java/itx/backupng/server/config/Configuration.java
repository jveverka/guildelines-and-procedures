package itx.backupng.server.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Configuration {

    private String serverId;
    private String rootDir;
    private RemoteSystem remoteSystem;

    @JsonCreator
    public Configuration(@JsonProperty("serverId") String serverId,
                         @JsonProperty("rootDir") String rootDir,
                         @JsonProperty("remoteSystem") RemoteSystem remoteSystem) {
        this.serverId = serverId;
        this.rootDir = rootDir;
        this.remoteSystem = remoteSystem;
    }

    public String getRootDir() {
        return rootDir;
    }

    public RemoteSystem getRemoteSystem() {
        return remoteSystem;
    }

    public boolean useRemoteCalls() {
        return remoteSystem!= null;
    }

    public String getServerId() {
        return serverId;
    }
}
