package itx.backupng.server.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GrpcServer {

    private int port;
    private String host;

    @JsonCreator
    public GrpcServer(@JsonProperty("host") String host,
                      @JsonProperty("port") int port) {
        this.port = port;
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

}
