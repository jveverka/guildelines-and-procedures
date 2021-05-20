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

```
./mongosh --username mongoadmin --password --host localhost:27017
show dbs
use my-db-name
db.createUser(
  {
    user: "my-admin",
    pwd: passwordPrompt(),
    roles: [
       { role: "userAdmin", db: "my-db-name" },
       { role: "dbOwner", db: "my-db-name" },
       { role: "readWrite", db: "my-db-name" }
    ]
  }
)
./mongosh --username my-admin --password --authenticationDatabase my-db-name "mongodb://localhost:27017/my-db-name
```
