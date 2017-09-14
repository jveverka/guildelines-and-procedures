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

DKEYFILE=$2
if [ "${DKEYFILE}xx" == "xx" ]; then
   echo "error: key file not specified"
   exit 3
fi

DKEYFILENEW=$3
if [ "${DKEYFILENEW}xx" == "xx" ]; then
   echo "error: new key file not specified"
   exit 4
fi

cryptsetup luksChangeKey --batch-mode --key-file ${DKEYFILE} --key-slot 0 ${DDEVICE}1 ${DKEYFILENEW}
if [ $? -ne 0  ]; then
   echo "error: luks change key has failed"
   exit 16
fi

exit 0

