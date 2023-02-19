# Ubuntu Troubleshooting and diagnostics
Created for Ubuntu 22.04 LTS

System logs
```shell
sudo dmesg -w
```
Logs from previous boot
```shell
journalctl -o short-precise -k -b -1
```
Install 6.1.x Kernel from [ubuntu mainline](https://kernel.ubuntu.com/~kernel-ppa/mainline/)
```shell
wget -c https://kernel.ubuntu.com/~kernel-ppa/mainline/v6.1.12/amd64/linux-headers-6.1.12-060112-generic_6.1.12-060112.202302141939_amd64.deb
wget -c https://kernel.ubuntu.com/~kernel-ppa/mainline/v6.1.12/amd64/linux-headers-6.1.12-060112_6.1.12-060112.202302141939_all.deb
wget -c https://kernel.ubuntu.com/~kernel-ppa/mainline/v6.1.12/amd64/linux-image-unsigned-6.1.12-060112-generic_6.1.12-060112.202302141939_amd64.deb
wget -c https://kernel.ubuntu.com/~kernel-ppa/mainline/v6.1.12/amd64/linux-modules-6.1.12-060112-generic_6.1.12-060112.202302141939_amd64.deb
sudo dpkg -i ./linux-*.deb
```
Search for packages
```shell
sudo apt-cache search linux-image | grep 6.1
```
