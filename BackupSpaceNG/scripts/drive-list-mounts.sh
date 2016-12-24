#!/bin/bash

cat /proc/mounts | grep btrfs
if [ $? -ne 0  ]; then
   echo "error: list drives has failed"
   exit 1
fi

exit 0

