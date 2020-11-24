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

## Run docker registry proxy
1. Start docker registry locally.
   ```
   docker run -d -p 6000:5000 \
      -e REGISTRY_PROXY_REMOTEURL=https://registry-1.docker.io \
      --restart always \
      --name registry registry:2
   ```
2. Configure docker to use local registry ``/etc/docker/daemon.json``.
   ```
   {
    "registry-mirrors": ["http://localhost:6000"]
   }
   ```
   Restart docker deamon.
   ```
   sudo systemctl stop docker
   sudo systemctl start docker
   ```
3. Pull some images.
   ```
   docker pull busybox
   ```
4. Check if local registry contains some docker images.
   ```
   curl --location --request GET 'http://localhost:6000/v2/_catalog'
   ```
   
