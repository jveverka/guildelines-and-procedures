# OpenVPN server setup
For basic setup, follow [this](https://help.ubuntu.com/lts/serverguide/openvpn.html.en) manual. 
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
For each client: 
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

