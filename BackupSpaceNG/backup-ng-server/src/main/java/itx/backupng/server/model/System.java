package itx.backupng.server.model;

import java.util.Collection;

public class System {

    private SystemInfo systemInfo;
    private Collection<BackupVolume> volumes;

    public System(SystemInfo systemInfo, Collection<BackupVolume> volumes) {
        this.systemInfo = systemInfo;
        this.volumes = volumes;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public Collection<BackupVolume> getVolumes() {
        return volumes;
    }

    public static class Builder {
        private SystemInfo systemInfo;
        private Collection<BackupVolume> volumes;

        public Builder setSystemInfo(SystemInfo systemInfo) {
            this.systemInfo = systemInfo;
            return this;
        }

        public Builder setVolumes(Collection<BackupVolume> volumes) {
            this.volumes = volumes;
            return this;
        }

        public Builder from(System system, SystemInfo systemInfo) {
            this.volumes = system.getVolumes();
            this.systemInfo = systemInfo;
            return this;
        }

        public Builder from(System system, Collection<BackupVolume> volumes) {
            this.volumes = volumes;
            this.systemInfo = system.getSystemInfo();
            return this;
        }

        public System build() {
            return new System(systemInfo, volumes);
        }

    }

}
