#!/bin/bash

SYSTEM_DISK=$1
if [ "${SYSTEM_DISK}xx" == "xx" ]; then
   echo "error: system disk not specified"
   exit 1
fi

ls -1 /dev/sd* | grep -v ${SYSTEM_DISK}
if [ $? -ne 0  ]; then
   echo "error: list disks has failed"
   exit 16
fi

exit 0

