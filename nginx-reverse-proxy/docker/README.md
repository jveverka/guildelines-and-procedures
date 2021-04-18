# NGINX as Docker

```
docker run -d --name mginx-proxy \
    --restart unless-stopped \
    -p 8070:8070 \
    nginx:1.19.10-alpine
```
