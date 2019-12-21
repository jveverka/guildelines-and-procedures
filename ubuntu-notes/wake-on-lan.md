# Wake-On LAN setup
This guide works only if WOL __Server__ and __client__ are on same ethernet LAN segment.

## Setup server
Enable WOL in BIOS. After that, follow [this](http://timtalbot.co.uk/setting-up-wake-on-lan-on-ubuntu-server-18-04-lts/) manual. 
```
sudo apt-get install ethtool
sudo vi /etc/systemd/system/wol.service
```
The content of ``wol.service`` is following:
```
[Unit]
Description=Configure Wake-up on LAN

[Service]
Type=oneshot
ExecStart=/sbin/ethtool -s enp35s0 wol g

[Install]
WantedBy=basic.target
```
Replace ``enp35s0`` with actual interface name and activate ``wol.service``.
```
sudo systemctl daemon-reload
sudo systemctl enable wol.service
sudo systemctl start wol.service
```

## Setup client
On client (PC which will send WOL packets), install ``etherwake``
```
sudo apt-get install etherwake
```
Wakeup target PC like this:
```
sudo etherwake -i eth0 00:01:02:03:04:05
```

