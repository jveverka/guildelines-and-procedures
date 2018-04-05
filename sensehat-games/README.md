#RaspberryPi SenseHAT games
This is simple implementation of selected RaspberryPi SenseHAT games in python3.
* pong
* snake
* tetris
* colors
* launcher.py - simple menu to launch the games

### Install & Setup
```
sudo apt-get install python3-pip
sudo apt-get install sense-hat
sudo apt-get install python3-pygame
pip3 install pygame
```

###Setup to autorun on startup
```
sudo crontab -e 
@reboot /opt/sensehat-games/launcher.py &
```

