# Data backup how-to
This sums up some of cmd line tools which may be used for data backups.

## rsync
Compare contect of 2 directories recursively
```
rsync -avnc ${SOURCE} ${DEST}
rsync -rvnc ${SOURCE}/ ${DEST}
```

Upload/synchonize local directory recursively to remote server, add new, delete removed files.
After this command, remote directory will be same as local.
```
rsync -avkSH --delete -e "ssh -i ${SSH_KEY}" ${SOURCE}/   ${BACKUP_USER}@${BACKUP_SERVER}:${BACKUP_ROOT}/${DEST}/
```

## ZFS

```
sudo apt install zfsutils-linux 
```

create ZFS mirrored pool
```
zpool create backup-volume mirror sde sdf
```

check the status of the pool
```
zpool status -v
```

start scrub on the pool
```
sudo zpool scrub backup-volume
```

create snapshot on the data pool
```
zfs snapshot -r backup-volume/juraj@2019-04-03
```

display snapshots
```
zfs list -t snapshot
```

Accessing the Snapshot files
```
ls -la /opt/backup-volume/.zfs/snapshot/juraj/2019-04-03
```

## SMART
Commandline tools for monitoring disks using S.M.A.R.T
```
sudo apt install smartmontools
```
Display smart info
```
sudo smartctl --all /dev/sda
sudo smartctl --quietmode=errorsonly --all /dev/sda
```
Start short smart tests and display results
```
sudo smartctl -t short /dev/sda
sudo smartctl -a /dev/sda
```

