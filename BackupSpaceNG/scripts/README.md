
create key file:
echo -e "password" > keyFile

./drive-destroy.sh /dev/sdb
./drive-init.sh /dev/sdb /opt/gbackup/scripts/keyFile
./drive-change-password.sh /dev/sdb /opt/gbackup/scripts/keyFile /opt/gbackup/scripts/keyFileNew
./drive-mount.sh /dev/sdb /opt/gbackup/scripts/keyFile masterData /mnt/masterData
./drive-umount.sh masterData /mnt/masterData


./drive-list-snapshots.sh /mnt/masterData
./drive-remove-snapshot.sh /mnt/masterData gergej/masterData_2016-02-19_22-50-00
./drive-create-snapshot.sh /mnt/masterData gergej
./drive-check.sh /mnt/masterData

remote execution:
ssh -i keys/remote root@192.168.30.150 /opt/gbackup/scripts/drive-list-mounts.sh

