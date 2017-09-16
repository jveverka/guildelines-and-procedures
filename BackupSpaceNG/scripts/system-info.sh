#!/bin/bash

SYSTEM_NAME=`cat /etc/*-release | grep PRETTY_NAME | awk -F"=" '{print $2}' | awk -F"\"" '{print $2}'`
echo "system-name=${SYSTEM_NAME}"

SYSTEM_KERNEL_VERSION=`uname -srm`
echo "system-kernel-version=${SYSTEM_KERNEL_VERSION}"

SYSTEM_HOSTNAME=`hostname`
echo "system-hostname=${SYSTEM_HOSTNAME}"

SYSTEM_UPTIME=`cat /proc/uptime | awk '{print $1}'`
echo "system-uptime=${SYSTEM_UPTIME}"

exit 0
