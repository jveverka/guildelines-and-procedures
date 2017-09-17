package itx.backupng.server.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Configuration {

    private String serverId;
    private String rootDir;
    private RemoteSystem remoteSystem;
    private GrpcServer grpcServer;
    private List<String> systemDriveIds;

    @JsonCreator
    public Configuration(@JsonProperty("serverId") String serverId,
                         @JsonProperty("rootDir") String rootDir,
                         @JsonProperty("remoteSystem") RemoteSystem remoteSystem,
                         @JsonProperty("grpcServer") GrpcServer grpcServer,
                         @JsonProperty("systemDriveIds") List<String> systemDriveIds) {
        this.serverId = serverId;
        this.rootDir = rootDir;
        this.remoteSystem = remoteSystem;
        this.grpcServer = grpcServer;
        this.systemDriveIds = systemDriveIds;
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

    public GrpcServer getGrpcServer() {
        return grpcServer;
    }

    public List<String> getSystemDriveIds() {
        return systemDriveIds;
    }

}
