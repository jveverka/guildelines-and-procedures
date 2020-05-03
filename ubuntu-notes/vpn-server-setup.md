# OpenVPN server setup
For basic setup, follow [this](https://ubuntu.com/server/docs/service-openvpn) manual. 
This guide describes pretty well how to setup server and clients.

## Static IP address for client
In some cases it is convenient, that OpenVPN client will get same IP address from OpenVPN server.
This is how server needs to be setup to achieve that:
In ``/etc/openvpn/server.conf`` add this line:
```
# Client config directory
client-config-dir /etc/openvpn/ccd
```
Create the ccd directory
```
sudo mkdir /etc/openvpn/ccd
``` 

## Create new client on VPN server
Create new openvpn client identity with static IP address setup.
* create/generate client's certificate
  ```
  cd /etc/openvpn/easy-rsa/
  source vars
  ./build-key client1
  ``` 
* create file in ``/etc/openvpn/ccd``, file name is equal to client id (client1) -> ``/etc/openvpn/ccd/client1``
* set file group and owner to __nobody__ ``sudo chown -R nobody:nogroup /etc/openvpn/ccd``
* content of the file is following
  ```
  ifconfig-push 10.8.0.10 255.255.255.0 
  ``` 
* make sure IP is listed in ``/var/log/openvpn/ipp.txt`` like this:
  ```
  client1,10.8.0.10 
  ```
* restart openVPN service
  ```
  sudo systemctl restart openvpn.service 
  ```

## Setup client machine (openvpn-client) on Utbuntu 18.04 LTS
Setup client operating system in order to connect to OpenVPN server.
```
sudo apt install openvpn
```
Copy the following files to the client using a secure method:
* ``/etc/openvpn/ca.crt`` -> ``/opt/openvpn-client/ca.crt``
* ``/etc/openvpn/easy-rsa/keys/client1.crt`` -> ``/opt/openvpn-client/client1.crt``
* ``/etc/openvpn/easy-rsa/keys/client1.key`` -> ``/opt/openvpn-client/client1.key``
* ``/etc/openvpn/ta.key`` -> ``/opt/openvpn-client/ta.key``

Create file ``/opt/openvpn-client/openvpn-client-config.ovpn`` with content:
```
client
dev tun
proto udp 
remote 3.120.95.27 1194
resolv-retry infinite
persist-key
persist-tun
verb 3

ca ca.crt
cert client1.crt
key client1.key
tls-auth ta.key 1

#comp-lzo
```

Create file ``/opt/openvpn-client/openvpn-client-connect.sh`` with content:
```
#!/bin/bash

echo "Starting VPN !"
sudo openvpn --config openvpn-client-config.ovpn 
```

Create file ``/opt/openvpn-client/openvpn-client-disconnect.sh`` with content:
```
#!/bin/bash


VPN_PID=`ps -ef | grep openvpn | grep openvpn-client | grep -v sudo | awk '{ print $2 }'`

if [ "${VPN_PID}" != "" ]; then
   echo "Stopping VPN process PID=${VPN_PID}"
   sudo kill ${VPN_PID}
else
   echo "VPN not running !"
fi
```

Create file ``/opt/openvpn-client/openvpn-client.service`` with content:
```
[Unit]
Description=dcvpn
After=network.target
After=systemd-user-sessions.service
After=network-online.target

[Service]
User=root
Type=simple
WorkingDirectory=/opt/openvpn-client
ExecStart=/opt/openvpn-client/openvpn-client-connect.sh
ExecStop=/opt/openvpn-client/openvpn-client-disconnect.sh
TimeoutSec=30
Restart=on-failure
RestartSec=30
StartLimitInterval=350
StartLimitBurst=10

[Install]
WantedBy=multi-user.target
```

Activate OpenVPN client service:
```
sudo cp openvpn-client.service /etc/systemd/system/
sudo chown root:root /etc/systemd/system/openvpn-client.service
sudo systemctl daemon-reload
sudo systemctl enable openvpn-client
sudo systemctl start openvpn-client
```


