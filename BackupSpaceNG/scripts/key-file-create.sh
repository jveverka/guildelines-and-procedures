#!/bin/bash

KEY_FILE_PATH=$1
if [ "${KEY_FILE_PATH}xx" == "xx" ]; then
   echo "error: key file path not specified"
   exit 1
fi

PASS_PHRASE=$2
if [ "${PASS_PHRASE}xx" == "xx" ]; then
   echo "error: key file path not specified"
   exit 2
fi

echo -e "${PASS_PHRASE}" > ${KEY_FILE_PATH}
if [ $? -ne 0  ]; then
   echo "error: key file create has failed"
   exit 16
fi

exit 0

