# PostgreSQL on Docker
1. Install docker 
```
sudo apt install docker.io
```

## PostgreSQL in docker container 
* Start PostgreSQL 9.5 as docker container:
```
mkdir -p ${HOME}/Data/docker-data/postgresql
docker run --name postgresql_9.5 -itd --restart always \
  --env 'PG_TRUST_LOCALNET=true' \
  --publish 5433:5432 \
  --volume ${HOME}/Data/docker-data/postgresql:/var/lib/postgresql \
  sameersbn/postgresql:9.5-2
```
* start PostgreSQL 10 as docker container:
```
mkdir -p ${HOME}/Data/docker-data/postgresql
docker run --name postgresql_10 -itd --restart always \
  --env 'PG_TRUST_LOCALNET=true' \
  --publish 5433:5432 \
  --volume ${HOME}/Data/docker-data/postgresql:/var/lib/postgresql \
  sameersbn/postgresql:10-2
```

## Clean database
* stop PostgreSQL docker container
  ```
  docker ps -a | grep sameersbn/postgresql
  docker stop <imageId>
  ```
* delete PostgreSQL persistent data
  ```
  sudo rm -rf ${HOME}/Data/docker-data/postgresql/9.5
  sudo rm -rf ${HOME}/Data/docker-data/postgresql/10
  ```
* start PostgreSQL docker container 
  ```
  docker stop <imageId>
  ```
* Restore database using ``pg_restore``  

## Use official PostgeSQL image
```
docker run -p 5432:5432 --name postgres-server -e POSTGRES_PASSWORD=secret -d postgres:12.3-alpine
docker stop postgres-server
docker rm postgres-server
```
