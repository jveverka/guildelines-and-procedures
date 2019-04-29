#!/bin/bash

# this sript sets up NAT between external interface (WAN) internal interface (LAN) 
# run as root on ubuntu 18.04 LTS
# set IPv4 packet forwarding by editing '/etc/sysctl.conf' and '/etc/ufw/sysctl.conf'
# https://www.ascinc.com/blog/linux/how-to-build-a-simple-router-with-ubuntu-server-18-04-1-lts-bionic-beaver/
# NAT setup eno1 is WAN interface, vboxnet0 is default gateway (192.168.56.1)
# WAN (eth0, eno1) 
# LAN (eth1, vboxnet0) 

WAN_IF=eno1
LAN_IF=vboxnet0

# Default policy to drop all incoming packets.
#iptables -P INPUT DROP
#iptables -P FORWARD DROP

# Accept incoming packets from localhost and the LAN interface.
iptables -A INPUT -i lo -j ACCEPT
iptables -A INPUT -i $LAN_IF -j ACCEPT

# Accept incoming packets from the WAN if the router initiated the connection.
iptables -A INPUT -i $WAN_IF -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT

# Forward LAN packets to the WAN.
iptables -A FORWARD -i $LAN_IF -o $WAN_IF -j ACCEPT

# Forward WAN packets to the LAN if the LAN initiated the connection.
iptables -A FORWARD -i $WAN_IF -o $LAN_IF -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT

# NAT traffic going out the WAN interface.
iptables -t nat -A POSTROUTING -o $WAN_IF -j MASQUERADE

exit 0

