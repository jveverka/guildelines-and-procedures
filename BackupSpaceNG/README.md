Backup Space NG
===============
Work in progress ...

Build and Run
-------------
```gradle clean build```

#### Deploy control scripts
```
sshpass -p secret ssh root@192.168.30.21 rm -rf /opt/backupng/scripts
sshpass -p secret scp -r scripts root@192.168.30.21:/opt/backupng/
```
