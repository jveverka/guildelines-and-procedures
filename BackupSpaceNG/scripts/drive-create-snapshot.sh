#!/bin/bash
# create snapshot of primary subvolume
#

PRIMARY_SUBVOLUME=primaryData

DMOUNTPOINT=$1
if [ "${DMOUNTPOINT}xx" == "xx" ]; then
   echo "error: drive mount point not specified"
   exit 1
fi

USER_NAME=$2
if [ "${USER_NAME}xx" == "xx" ]; then
   echo "error: user name not specified"
   exit 2
fi

DSUBVOLUME=`date +'%Y-%m-%d_%H-%M-%S'`
DSUBVOLUME=${DSUBVOLUME}_${PRIMARY_SUBVOLUME}

btrfs subvolume snapshot ${DMOUNTPOINT}/${USER_NAME}/${PRIMARY_SUBVOLUME} ${DMOUNTPOINT}/${USER_NAME}/${DSUBVOLUME}
if [ $? -ne 0  ]; then
   echo "error: btrfs subvolume snapshot has failed"
   exit 16
fi

exit 0

