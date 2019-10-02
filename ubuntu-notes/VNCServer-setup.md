# VNC Server setup
This guide describes VNC server setup for Ubuntu 18.04 LTS.

### Installation
```
sudo apt install xfce4 xfce4-goodies
sudo apt install tightvncserver
```

### First Start
* Start ``vncserver`` for first time. Example below shows that instance #1 was started.
  ```
  vncserver
  You will require a password to access your desktops.
  Password: 
  Verify:   
  Would you like to enter a view-only password (y/n)? n
  New 'X' desktop is juraj-HP-Z4-G4-Workstation:1
  ```
* Stop server after started for first time. Assuming instance #1 is running.
  ```
  vncserver -kill :1
  ```
* Edit config file ``.vnc/xstartup`` configuration file.
  * comment out last line ``#/etc/X11/Xsession`` 
  * add new lasts line ``startxfce4 &``

### Normal usage
* Start VNC server, resolutions: 1024x768, 1280x1024, 1280×720, 1440x900, 1920×1080
  ```
  vncserver -geometry 1440x900
  ```
* Stop VNC server
  ```
  vncserver -kill :1
  ```
  