#!/bin/bash

DMOUNTPOINT=$1
if [ "${DMOUNTPOINT}xx" == "xx" ]; then
   echo "error: drive mount point not specified"
   exit 1
fi

DSUBVOLUME=$2
if [ "${DSUBVOLUME}xx" == "xx" ]; then
   echo "error: subvolume name not specified"
   exit 1
fi

btrfs subvolume delete ${DMOUNTPOINT}/${DSUBVOLUME}
if [ $? -ne 0  ]; then
   echo "error: btrfs subvolume delete has failed"
   exit 1
fi

echo 0
 
