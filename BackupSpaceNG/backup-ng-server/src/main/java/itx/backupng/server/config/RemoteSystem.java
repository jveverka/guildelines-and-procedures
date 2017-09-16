package itx.backupng.server.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoteSystem {

    private String host;
    private String user;
    private String password;

    @JsonCreator
    public RemoteSystem(@JsonProperty("host") String host,
                        @JsonProperty("user") String user,
                        @JsonProperty("password") String password) {
        this.host = host;
        this.user = user;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
