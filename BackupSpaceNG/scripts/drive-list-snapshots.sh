#!/bin/bash
# list subvolume list on data drive 
#

DMOUNTPOINT=$1
if [ "${DMOUNTPOINT}xx" == "xx" ]; then
   echo "error: drive mount point not specified"
   exit 1
fi

btrfs subvolume list ${DMOUNTPOINT}
if [ $? -ne 0  ]; then
   echo "error: btrfs subvolume list has failed"
   exit 16
fi

exit 0

