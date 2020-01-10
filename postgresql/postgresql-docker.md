# PostgreSQL on Docker
1. Install docker 
```
sudo apt install docker.io
```

## PostgreSQL in docker container 
Start PostgreSQL 9.5 as docker container:
```
mkdir -p ${HOME}/docker-data/local-dev
docker run --name webflow_db -itd --restart always \
  --env 'PG_TRUST_LOCALNET=true' \
  --publish 5433:5432 \
  --volume ${HOME}/docker-data/local-dev:/var/lib/postgresql \
  sameersbn/postgresql:9.5-2
```

## Clean database
* stop PostgreSQL docker container
  ```
  docker ps -a | grep sameersbn/postgresql:9.5-2
  docker stop <imageId>
  ```
* delete PostgreSQL persistent data
  ```
  sudo rm -rf ${HOME}/docker-data/local-dev/9.5
  ```
* start PostgreSQL docker container 
  ```
  docker stop <imageId>
  ```
* Restore database using ``pg_restore``  
