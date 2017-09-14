#!/bin/bash

DLUKSNAME=$1
if [ "${DLUKSNAME}xx" == "xx" ]; then
   echo "error: luks name not specified"
   exit 1
fi

DMOUNTPOINT=$2
if [ "${DMOUNTPOINT}xx" == "xx" ]; then
   echo "error: luks mount point not specified"
   exit 2
fi

umount ${DMOUNTPOINT}
if [ $? -ne 0  ]; then
   echo "error: drive unmount has failed"
   exit 3
fi

cryptsetup luksClose ${DLUKSNAME}
if [ $? -ne 0  ]; then
   echo "error: luks close has failed"
   exit 16
fi

exit 0

