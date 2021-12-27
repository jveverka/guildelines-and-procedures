# Running ARMv64 VM on AMD64
Simple guide how to install and run ARMv64 virtual machine on x86_64 hardware using [qemu](https://www.qemu.org/) emulator.
* Install dependencies on host VM.
```
sudo apt install qemu-system-arm qemu-efi-aarch64 qemu-utils
```
* Create boot images.
```
dd if=/dev/zero of=flash1.img bs=1M count=64
dd if=/dev/zero of=flash0.img bs=1M count=64
dd if=/usr/share/qemu-efi-aarch64/QEMU_EFI.fd of=flash0.img conv=notrunc
```
* Create empty root image.
```
qemu-img create disk-image.img 20G
``` 
* Start vm for the first time and install system on ``disk-image.img``.
```
./start-install-vm.sh    
```
* Run VM after fully installed.
```
./start-vm.sh 
``` 
* Connect to running VM:
```
ssh user@127.0.0.1 -p 2222
``` 

### References:
* https://futurewei-cloud.github.io/ARM-Datacenter/qemu/how-to-launch-aarch64-vm/
 