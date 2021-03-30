# MongoDB

```
docker run -d --name mongo-db \
    --restart unless-stopped \
    -p 27017:27017 \
    -e MONGO_INITDB_ROOT_USERNAME=mongoadmin \
    -e MONGO_INITDB_ROOT_PASSWORD=secret \
    -v /opt/data/mongo-data:/data/db \
    mongo:4.4.4-bionic
```
