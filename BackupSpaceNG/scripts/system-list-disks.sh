#!/bin/bash

echo "disks-by-uuid:"
ls -l /dev/disk/by-uuid
echo "disks-by-id:"
ls -l /dev/disk/by-id
echo "disks-by-path:"
ls -l /dev/disk/by-path
if [ $? -ne 0  ]; then
   echo "error: list disks has failed"
   exit 16
fi

exit 0
