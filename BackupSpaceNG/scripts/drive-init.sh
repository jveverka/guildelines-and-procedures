#!/bin/bash
# initialize data drive, all data are deleted/lost 
# 1. fully format drive, create new partition
# 2. create luks partition
# 3. format as btrfs file system 
# 4. create primary data subvolume
# 5. unmount
#

PRIMARY_SUBVOLUME=primaryData

DDEVICE=$1
if [ "${DDEVICE}xx" == "xx" ]; then
   echo "error: disk device not specified"
   exit 1
fi

DTYPE=`ls -la ${DDEVICE} | cut -c1`
if [ "${DTYPE}" != "b" ]; then
   echo "error: block device expected"
   exit 2
fi

DKEYFILE=$2
if [ "${DKEYFILE}xx" == "xx" ]; then
   echo "error: key file not specified"
   exit 3
fi

#create single full size partition on disk drive 
echo -e "n\n\n\n\n\nw\n" | fdisk ${DDEVICE}
if [ $? -ne 0  ]; then
   echo "error: disk partitioning has failed"
   exit 1 
fi
sync

#setup luks partition
echo "setting up luks partition ..."
#KEYSIZE=`ls -l /opt/keyFile | awk '{ print $5 }'`
cryptsetup luksFormat --batch-mode --cipher aes --key-file ${DKEYFILE} --key-size 256 --key-slot 0 ${DDEVICE}1
if [ $? -ne 0  ]; then
   echo "error: luks setup has failed"
   exit 1
fi
sync

#open luks partition
echo "opening luks partition ..."
#KEYSIZE=`ls -l /opt/keyFile | awk '{ print $5 }'`
cryptsetup luksOpen --batch-mode --cipher aes --key-file ${DKEYFILE} --key-slot 0 ${DDEVICE}1 newBackupDrive
if [ $? -ne 0  ]; then
   echo "error: luks open has failed"
   exit 1
fi
sync

#format drive using btrfs
echo "formatting luks partition using btrfs ..."
mkfs -t btrfs -f /dev/mapper/newBackupDrive
if [ $? -ne 0  ]; then
   echo "error: btrfs formatting has failed"
   exit 1
fi

#test mount new btrfs partition
echo "creating driveInfo.txt file ..."
PRIMARY_SUBVOLUME_CREATE_FAILED=0
mkdir -p /mnt/newBackupDrive
mount -t btrfs /dev/mapper/newBackupDrive /mnt/newBackupDrive
if [ $? -ne 0  ]; then
   echo "warning: btrfs test mount has failed"
else 
   #write drive created info on new brtfs partition
   echo "drive created" > /mnt/newBackupDrive/driveInfo.txt
   if [ $? -ne 0  ]; then
      PRIMARY_SUBVOLUME_CREATE_FAILED=1
      echo "warning: driveInfo.txt creation has failed"
   fi 

   btrfs subvolume create /mnt/newBackupDrive/${PRIMARY_SUBVOLUME}
   if [ $? -ne 0  ]; then
      PRIMARY_SUBVOLUME_CREATE_FAILED=1
      echo "warning: btrfs primary subvolume create has failed"
   fi
 
   #unmount new btrfs partition
   umount /mnt/newBackupDrive
   if [ $? -ne 0  ]; then
      PRIMARY_SUBVOLUME_CREATE_FAILED=1
      echo "warning: btrfs test unmount has failed"
   fi

fi

#close luks drive
echo "closing luks partition ..."
cryptsetup luksClose newBackupDrive
if [ $? -ne 0  ]; then
   echo "error: luks close has failed"
   exit 1
fi

if [ ${PRIMARY_SUBVOLUME_CREATE_FAILED} -ne 0 ]; then
   echo "error: drive setup has failed"
   exit 1
fi

echo "done."
exit 0

