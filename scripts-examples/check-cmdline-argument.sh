#!/bin/bash

ARG1=$1

if [ "${ARG1}xx" == "xx" ]; then
   echo "ERROR: expected one commandline argument"
   exit 1
else
   echo "ARG1 = ${ARG1}"
fi

exit 0
