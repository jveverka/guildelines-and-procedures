# NGINX in Docker

```
export ARCH=amd64|arm64v8
docker stop nginx-proxy;
docker rm nginx-proxy;
docker build -t nginx-proxy .;
docker run -d --name nginx-proxy \
    --restart unless-stopped \
    -p 8070:8070 \
    --network host \
    nginx-proxy
```
