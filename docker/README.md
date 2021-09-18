# Docker guidelines
See also [kubernetes guidelines](../kubernetes)
## Install Docker on [Ubuntu 20.04](https://releases.ubuntu.com/20.04.3/ubuntu-20.04.3-live-server-amd64.iso)
```
sudo apt install -y docker.io
sudo apt install -y docker-compose
sudo systemctl enable --now docker
sudo usermod -aG docker ${USER}
```

## Install Docker on [Centos 8](http://ftp.sh.cvut.cz/centos/8.4.2105/isos/x86_64/CentOS-8.4.2105-x86_64-boot.iso)
```
sudo yum install -y yum-utils
sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
sudo yum remove runc
sudo yum install docker-ce docker-ce-cli containerd.io
sudo systemctl start docker

curl -L "https://github.com/docker/compose/releases/download/1.27.4/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
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

### References
* [About /var/run/docker.sock](https://betterprogramming.pub/about-var-run-docker-sock-3bfd276e12fd)
* /var/lib/docker/*
* /var/lib/docker/containers/*
