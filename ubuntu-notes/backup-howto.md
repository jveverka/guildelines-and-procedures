# Data backup how-to
This sums up some of cmd line tools which may be used for data backups.

## rsync
Compare contect of 2 directories recursively
```shell
rsync -avnc ${SOURCE} ${DEST}
rsync -rvnc ${SOURCE}/ ${DEST}
```

Upload/synchonize local directory recursively to remote server, add new, delete removed files.
After this command, remote directory will be same as local.
```shell
rsync -avkSH --delete -e "ssh -i ${SSH_KEY}" ${SOURCE}/   ${BACKUP_USER}@${BACKUP_SERVER}:${BACKUP_ROOT}/${DEST}/
```

## ZFS
Install ZFS on ubuntu.
```shell
sudo apt install zfsutils-linux 
```

Create ZFS mirrored pool
```shell
zpool create backup-volume mirror sde sdf
```

Check the status of the pool
```shell
zpool status -v
```

Start scrub on the pool
```shell
sudo zpool scrub backup-volume
```

Create snapshot on the data pool
```shell
zfs snapshot -r backup-volume/juraj@2019-04-03
```

Display snapshots
```shell
zfs list -t snapshot
```

Accessing the Snapshot files
```shell
ls -la /opt/backup-volume/.zfs/snapshot/juraj/2019-04-03
```

### ZFS Single Volume Management
* Create and mount new ZFS pool on single device `/dev/sda`
  ```shell
  sudo zpool create backup-volume sda
  sudo zpool scrub backup-volume
  # Data is available on /backup-volume
  ```
* Unmount ZFS pool on device `/dev/sda`
  ```shell
  sudo zpool export backup-volume
  ```
* List all devices in the system. This command searches for devices.
  ```shell
  sudo zpool import
  ``` 
* Re-mount existing ZFS pool
  ```shell
  sudo zpool import backup-volume
  ```

## SMART
Commandline tools for monitoring disks using S.M.A.R.T
```shell
sudo apt install smartmontools
```
Display smart info
```shell
sudo smartctl --all /dev/sda
sudo smartctl --quietmode=errorsonly --all /dev/sda
```
Start short smart tests and display results
```shell
sudo smartctl -t short /dev/sda
sudo smartctl -a /dev/sda
```
## NVMe disk checks
```shell
sudo apt install nvme-cli
sudo nvme list
sudo nvme smart-log /dev/nvme0n1
sudo nvme error-log /dev/nvme0n1
```
