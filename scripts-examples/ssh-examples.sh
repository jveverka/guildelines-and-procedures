#!/bin/bash

#ssh accepts any key/fingerprint and uses /dev/null as known hosts
ssh -p 8101 -oStrictHostKeyChecking=no -oUserKnownHostsFile=/dev/null user@192.168.56.101 ls -la

#ssh same as above with password 
sshpass -p password ssh -p 8101 -oStrictHostKeyChecking=no -oUserKnownHostsFile=/dev/null user@192.168.56.101 ls -la 

# connect ssh using key
ssh -i keyfile user@192.168.56.101
