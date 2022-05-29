# PostgreSQL in Docker

* Official [postgres docker](https://hub.docker.com/_/postgres).
* Running [multiple databases in single docker container](postgresql-multiple-dbs/README.md).

```
docker run -p 5432:5432 --name postgres-server \
  -e POSTGRES_PASSWORD=secret \
  -e POSTGRES_USER=user \
  -e POSTGRES_DB=testdb \
  -e PGDATA=/var/lib/postgresql/data/pgdata \
  -v ${HOME}/Data/tmp/pg-data:/var/lib/postgresql/data \
  -d postgres:14.3-alpine
  
docker stop postgres-server
docker rm postgres-server

#attach to running container to check databases and tables
docker container exec -it postgres-server /bin/bash
# psql -U postgres
```
