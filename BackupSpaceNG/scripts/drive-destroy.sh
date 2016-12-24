#!/bin/bash

DDEVICE=$1
if [ "${DDEVICE}" == "" ]; then
   echo "error: disk device not specified"
   exit 1
fi

DTYPE=`ls -la ${DDEVICE} | cut -c1`
if [ "${DTYPE}" != "b" ]; then
   echo "error: block device expected"
   exit 2
fi

cryptsetup luksErase --batch-mode ${DDEVICE}1
if [ $? -ne 0  ]; then
   echo "warning: luks erase has failed"
fi

dd bs=512 count=63 if=/dev/zero of=${DDEVICE}
if [ $? -ne 0  ]; then
   echo "error: dd partition cleanup failed"
   exit 1
fi

sync

exit 0

