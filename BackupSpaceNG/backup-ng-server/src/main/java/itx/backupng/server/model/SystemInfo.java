package itx.backupng.server.model;

public class SystemInfo {

    private String hostName;
    private String kernelVersion;
    private String distributionName;
    private Long uptime;

    public SystemInfo(String hostName, String kernelVersion, String distributionName, Long uptime) {
        this.hostName = hostName;
        this.kernelVersion = kernelVersion;
        this.distributionName = distributionName;
        this.uptime = uptime;
    }

    public String getHostName() {
        return hostName;
    }

    public Long getUptime() {
        return uptime;
    }

    public String getKernelVersion() {
        return kernelVersion;
    }

    public String getDistributionName() {
        return distributionName;
    }

    public static class Builder {
        private String hostName;
        private String kernelVersion;
        private String distributionName;
        private Long uptime;

        public Builder setHostName(String hostName) {
            this.hostName = hostName;
            return this;
        }

        public Builder setUptime(Long uptime) {
            this.uptime = uptime;
            return this;
        }

        public Builder setKernelVersion(String kernelVersion) {
            this.kernelVersion = kernelVersion;
            return this;
        }

        public Builder setDistributionName(String distributionName) {
            this.distributionName = distributionName;
            return this;
        }

        public SystemInfo build() {
            return new SystemInfo(hostName, kernelVersion, distributionName, uptime);
        }

    }

}
