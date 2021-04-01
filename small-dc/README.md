# Small/home Data Center

This is project of small (potentially portable) data center.
Main goals of this project: 
* kubernetes practicing.
* distributed systems / clustering testing.
* microservice playground.
* networking playground.
* low  power design 25W idle. 

![architecture](docs/architecture-01.svg)

Internal DC network: 192.168.40.0/24

## DC gateway

## Public OpenVPN server

## Accessing DC
1. From local network, add local route to DC gateway. This is excample for Ubuntu 18.04 LTS client:
   ```sudo ip route add 192.168.40.0/24 via 192.168.0.40 dev enp14s0``` 
   Where 192.168.0.40 is public IP address of DC gateway and enp14s0 is name of local client interface.
2. From public internet using OpenVPN.


