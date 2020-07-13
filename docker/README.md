# Docker guidelines

## Install Docker on Ubuntu 20.04
```
sudo apt install docker.io
sudo apt install docker-compose
sudo systemctl enable --now docker
sudo usermod -aG docker MYUSERNAME
```

## Install additional tools
* [dive](https://github.com/wagoodman/dive) - explore docker images in console.
