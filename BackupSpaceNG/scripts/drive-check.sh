#!/bin/bash

DMOUNTPOINT=$1
if [ "${DMOUNTPOINT}xx" == "xx" ]; then
   echo "error: luks mount point not specified"
   exit 1
fi

btrfs scrub start -B ${DMOUNTPOINT}
if [ $? -ne 0  ]; then
   echo "error: btrfs scrub has failed"
   exit 16
fi

exit 0

