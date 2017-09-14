#!/bin/bash

DSRCDIR=$1
if [ "${DSRCDIR}xx" == "xx" ]; then
   echo "error: source directory not specified"
   exit 1
fi

DSTDIR=$2
if [ "${DSTDIR}xx" == "xx" ]; then
   echo "error: destination directory not specified"
   exit 2
fi


rsync -avkSH --delete ${DSRCDIR}/ ${DSTDIR}
if [ $? -ne 0  ]; then
   echo "error: data sync has failed"
   exit 16
fi

exit 0


