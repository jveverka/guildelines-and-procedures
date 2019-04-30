# Ubuntu 18.04 setup notes
Collection of notes for setting varios things on Ubuntu 18.04 LTS.

### Edit application launcher 
```cd .local/share/applications/```
edit application launcher, for example:
```vi jetbrains-idea-ce.desktop```

### mount drive over ssh
```
sudo apt-get install sshfs
sudo sshfs -o allow_other,defer_permissions root@192.168.0.249:/opt/ /mnt/remote-ssh/
```

### Network interface setup
As root create file 
```/etc/netplan/01-netcfg.yaml```
with content like this:
```
network:
  version: 2
  renderer: networkd
  ethernets:
    enp0s3:
      dhcp4: no
      dhcp6: no
      addresses: [192.168.56.102/24, ]
      gateway4: 192.168.56.1
      nameservers: 
         addresses: [8.8.8.8, 8.8.4.4]
```
than apply settings by
```
netplan apply
```
This network setup survives reboots.

