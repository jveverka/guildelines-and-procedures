# Ubuntu 22.04 setup notes
Collection of notes, setting and various guidelines for Ubuntu 20.04 LTS.

## Desktop after fresh install
1. Install additional packages
   ```
   sudo apt install gnome-tweaks 
   ```
2. install [docker and docker-compose](../docker)

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
then apply settings by
```
netplan apply
```
This network setup survives reboots.

## Intellij Idea Favorite
* Copy ``jetbrains-idea.desktop`` file into location below:
   ```
   ~/.local/share/applications/jetbrains-idea.desktop
   ```
* Edit file content
* Start intellij from CLI and create favorite link (right click).

## nvme command line
```shell
sudo apt install nvme-cli
sudo nvme smart-log /dev/nvme0
```