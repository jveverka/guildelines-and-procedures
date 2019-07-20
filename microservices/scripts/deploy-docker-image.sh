#!/bin/bash

IMAGE_FILE_PATH=$1
IMAGE_FILE=`basename $IMAGE_FILE_PATH`
IMAGE_TAG=`basename -s .tar $IMAGE_FILE_PATH`

echo "IMAGE_FILE_PATH=$IMAGE_FILE_PATH"
echo "IMAGE_FILE=$IMAGE_FILE"
echo "IMAGE_TAG=$IMAGE_TAG"
#exit 1

scp -i controlkey.pri $IMAGE_FILE_PATH juraj@192.168.56.101:/opt/images/
ssh -i controlkey.pri juraj@192.168.56.101 docker import /opt/images/$IMAGE_FILE $IMAGE_TAG

scp -i controlkey.pri $IMAGE_FILE_PATH juraj@192.168.56.102:/opt/images/
ssh -i controlkey.pri juraj@192.168.56.102 docker import /opt/images/$IMAGE_FILE $IMAGE_TAG

scp -i controlkey.pri $IMAGE_FILE_PATH juraj@192.168.56.103:/opt/images/
ssh -i controlkey.pri juraj@192.168.56.103 docker import /opt/images/$IMAGE_FILE $IMAGE_TAG

