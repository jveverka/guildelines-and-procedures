#!/bin/bash

KEY_FILE_PATH=$1
if [ "${KEY_FILE_PATH}xx" == "xx" ]; then
   echo "error: key file path not specified"
   exit 1
fi

shred -z -u ${KEY_FILE_PATH}
if [ $? -ne 0  ]; then
   echo "error: key delete has failed"
   exit 16
fi

exit 0

