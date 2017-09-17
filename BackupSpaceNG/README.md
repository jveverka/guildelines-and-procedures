Backup Space NG
===============
System for keeping consistent and secure home backups. 
Work in progress ...

Architecture
------------
![Architecture](docs/architecture-overview.png)

__Components__
* BackupSpaceNG server - command line client.
* BackupSpaceNG CLI - service layer for consistent client access.
* [Control Scripts](scripts/README.md) - control scripts for managing backup drives.

Backup Drive
------------
![BackupDrive](docs/backup-drive.png)

Build and Run
-------------
```gradle clean build```

#### Deploy control scripts
```
sshpass -p secret ssh root@192.168.30.21 rm -rf /opt/backupng/scripts
sshpass -p secret scp -r scripts root@192.168.30.21:/opt/backupng/
```
