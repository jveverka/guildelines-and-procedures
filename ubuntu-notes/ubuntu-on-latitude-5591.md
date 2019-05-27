# Ubuntu 18.04.2 LTS on Latitude 5591 - Issues
How to make work Ubuntu on new Latitude 5591:
Intel® CoreTM i7 8850H - NVIDIA GeForce® MX130 2 GB GDDR5 and ThunderboltTM 3

After the installation was completed, I reboot my computer, enter credentials in login screen and then the OS freezes. No way to reach the desktop. Issue can be fixed fixed with this procedure:

1. on your login page use "ctrl+alt+f2" to access terminal. (before typing password when computer is still not frozen).
2. in terminal use ``sudo add-apt-repository ppa:graphics-drivers``
3. update the ppa you add ``sudo apt-get update``
4. install the latest nvidia driver ``sudo apt-get install nvidia-390``
5. let the installation complete and then restart your pc and enjoy.

## Install Oracle VirtualBox
Do not install Ubuntu default package (virtualbox 5.2), install latest one from Oracle instead.
1. add VirtualBox apt source into ``/etc/apt/sources.list``
   ```
   deb https://download.virtualbox.org/virtualbox/debian bionic contrib
   ```
2. import oracle keys
   ```
   wget -q https://www.virtualbox.org/download/oracle_vbox_2016.asc -O- | sudo apt-key add -
   wget -q https://www.virtualbox.org/download/oracle_vbox.asc -O- | sudo apt-key add -
   ```
3. install packages
   ```
   sudo apt update
   sudo apt install virtualbox-6.0
   ``` 
4. configure VirtualBox
   ```
   sudo /sbin/vboxconfig
   ``` 
5. Next step is [signing vboxdrv kernel module](https://stegard.net/2016/10/virtualbox-secure-boot-ubuntu-fail/) for EFI secure boot.
https://askubuntu.com/questions/1002096/uefi-secure-boot-unable-to-sign-virtualbox-kernel-modules-sign-key-does-noth

