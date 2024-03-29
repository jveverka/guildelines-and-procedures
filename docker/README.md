# Docker guidelines
See also [kubernetes guidelines](../kubernetes)
## Install Docker on [Ubuntu 20.04](https://releases.ubuntu.com/20.04.3/ubuntu-20.04.3-live-server-amd64.iso)
```
sudo apt install -y docker.io
sudo apt install -y docker-compose
sudo systemctl enable --now docker
sudo usermod -aG docker ${USER}
sudo systemctl start docker
```

## Install Docker on [Centos 8](http://ftp.sh.cvut.cz/centos/8.4.2105/isos/x86_64/CentOS-8.4.2105-x86_64-boot.iso)
```
sudo yum update -y
sudo yum install -y yum-utils
sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
sudo yum remove -y runc
sudo yum install -y docker-ce docker-ce-cli containerd.io
sudo systemctl enable docker
sudo systemctl start docker

curl -L "https://github.com/docker/compose/releases/download/1.27.4/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
```

## Install Docker on [Rocky Linux 8](https://rockylinux.org/download)
  ```
  sudo yum update -y
  sudo yum install -y yum-utils
  sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
  sudo yum install -y docker-ce docker-ce-cli containerd.io
  sudo systemctl enable docker
  sudo systemctl start docker
  ```

## Install additional tools
* [dive](https://github.com/wagoodman/dive) - explore docker images in console.

## Build Docker image for x86_64 and ARM64
```
export REPOSITORY=jurajveverka
export IMAGE_NAME=docker-example
export VERSION=1.0.0
 
# on x86 AMD64 device:
docker build -t ${REPOSITORY}/${IMAGE_NAME}:${VERSION}-amd64 --build-arg ARCH=amd64 --file ./Dockerfile . 
docker push ${REPOSITORY}/${IMAGE_NAME}:${VERSION}-amd64

# on ARM64 v8 device (RPi4 64bit OS):
docker build -t ${REPOSITORY}/${IMAGE_NAME}:${VERSION}-arm64v8 --build-arg ARCH=arm64v8 --file ./Dockerfile . 
docker push ${REPOSITORY}/${IMAGE_NAME}:${VERSION}-arm64v8

# on x86 AMD64 device: 
docker manifest create \
${REPOSITORY}/${IMAGE_NAME}:${VERSION} \
--amend ${REPOSITORY}/${IMAGE_NAME}:${VERSION}-amd64 \
--amend ${REPOSITORY}/${IMAGE_NAME}:${VERSION}-arm64v8

docker manifest push ${REPOSITORY}/${IMAGE_NAME}:${VERSION}
```

## Uninstall Docker [Ubuntu 20.04](https://releases.ubuntu.com/20.04.3/ubuntu-20.04.3-live-server-amd64.iso)
```
sudo apt purge -y docker.io
sudo apt purge -y docker-compose
sudo apt autoremove
```

## Remote access
```
useradd dockeradmin
usermod -aG docker dockeradmin

docker context create my-remote-docker-machine --docker "host=ssh://dockeradmin@host:port"
docker context ls
docker context use my-remote-docker-machine
```

### References
* [Docker Gotchas](https://uilicious.com/blog/5-fatal-docker-gotchas-for-new-users/)
* [About /var/run/docker.sock](https://betterprogramming.pub/about-var-run-docker-sock-3bfd276e12fd)
* [Docker Context](https://docs.docker.com/engine/context/working-with-contexts/)
* /var/lib/docker/*
* /var/lib/docker/containers/*
