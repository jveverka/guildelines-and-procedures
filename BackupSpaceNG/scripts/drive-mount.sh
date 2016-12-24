#!/bin/bash

DDEVICE=$1
if [ "${DDEVICE}xx" == "xx" ]; then
   echo "error: disk device not specified"
   exit 1
fi

DTYPE=`ls -la ${DDEVICE} | cut -c1`
if [ "${DTYPE}" != "b" ]; then
   echo "error: block device expected"
   exit 1
fi

DKEYFILE=$2
if [ "${DKEYFILE}xx" == "xx" ]; then
   echo "error: key file not specified"
   exit 1
fi

DLUKSNAME=$3
if [ "${DLUKSNAME}xx" == "xx" ]; then
   echo "error: luks name not specified"
   exit 1
fi

DMOUNTPOINT=$4
if [ "${DMOUNTPOINT}xx" == "xx" ]; then
   echo "error: luks mount point not specified"
   exit 1
fi

#open luks partition
echo "opening luks partition ..."
cryptsetup luksOpen --batch-mode --cipher aes --key-file ${DKEYFILE} --key-slot 0 ${DDEVICE}1 ${DLUKSNAME} 
if [ $? -ne 0  ]; then
   echo "error: luks open has failed"
   exit 1
fi

mkdir -p ${DMOUNTPOINT}
mount /dev/mapper/${DLUKSNAME} ${DMOUNTPOINT}
if [ $? -ne 0  ]; then
   echo "error: drive mount has failed"
   exit 1
fi

exit 0

