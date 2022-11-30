# Elastic - Kibana stack

## Run 3-Node stack in Docker compose
```
docker-compose -f ek-docker-compose-3n.yml up -d
docker-compose -f ek-docker-compose-3n.yml down -v 
docker-compose -f ek-docker-compose-3n.yml down -v --rmi all --remove-orphans
```
