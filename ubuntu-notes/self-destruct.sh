#!/bin/bash

# useful script when purging data from PC

read -p "Do you REALLY wish to purge ALL private data from this PC ? " yn
if [ ${yn} == "y" ]; then
   echo "Purging personal data ..."
   echo "/home/${USER}"
   
   #exit 1	
   
   rm -Prf /home/${USER}/.ssh/*
   rm -rf /home/${USER}/Data/Private
   rm -rf /home/${USER}/.thunderbird
   rm -rf /home/${USER}/.mozilla
   rm -rf /home/${USER}/.config/google-chrome

   cat /dev/urandom >> /home/${USER}/thebigblob.data
   rm -rf /home/${USER}/thebigblob.data

   echo "Leaving company, Thank you and bye bye !"
   echo "done."
   exit 0
else
   echo "NO, I want my mummy back, purge abborted !"
   echo "done."
   exit 1
fi



