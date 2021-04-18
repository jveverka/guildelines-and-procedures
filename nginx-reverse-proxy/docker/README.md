# NGINX as Docker

```
docker run -d --name nginx-proxy \
    --restart unless-stopped \
    -p 8070:8070 \
    --network host \
    nginx-proxy
```
