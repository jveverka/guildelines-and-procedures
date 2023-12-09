# Elastic - Kibana stack
Based on [official documentation](https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html#_start_your_cluster_with_security_enabled_and_configured).

## Prerequisites
Set max_map_count to 262144
```shell
sysctl -w vm.max_map_count=262144
```
Persistent change must be done in ``/etc/sysctl.conf``


## Run 1-Node stack in Docker compose
```shell
docker-compose -f ek-docker-compose-1n.yml up -d
docker-compose -f ek-docker-compose-1n.yml down -v 
docker-compose -f ek-docker-compose-1n.yml down -v --rmi all --remove-orphans
```

## Run 1-Node stack in Docker Swarm mode
1. Setup 
```shell
mkdir -p /mnt/data/elastic/certs
mkdir -p /mnt/data/elastic/es-data
mkdir -p /mnt/data/elastic/kibana-data
docker-compose -f ek-docker-ek-stack-setup.yml up -d
docker logs juraj_setup_1
sudo ls -la /mnt/data/elastic/certs
docker-compose -f ek-docker-ek-stack-setup.yml down -v 
```
2. Start & Stop ek stack
```shell
curl -k -X POST -u "elastic:u01S3crtAB7z" -H "Content-Type: application/json" https://localhost:9200/_security/user/kibana_system/_password -d "{\"password\":\"u01S3crtAB7z\"}"
sudo sysctl -w vm.max_map_count=262144
docker stack deploy -c ek-docker-ek-stack-swarm-1n.yml ek-stack
docker stack rm ek-stack
docker stack ps ek-stack
docker stack services ek-stack
docker volume inspect ek-stack_elastic_data 
```
3. Remove / Uninstall
```shell
docker stack rm ek-stack
rm -rf /mnt/data/elastic
``` 

## Run 3-Node stack in Docker compose
```
docker-compose -f ek-docker-compose-3n.yml up -d
docker-compose -f ek-docker-compose-3n.yml down -v 
docker-compose -f ek-docker-compose-3n.yml down -v --rmi all --remove-orphans
```

## Connect to Kibana & ES
* Kibana URL: http://localhost:5601/   
  elastic : u01S3crtAB7z 
* Elastic URL
  ```shell
  curl --request GET 'https://localhost:9200/_cluster/health' \
  --header 'Authorization: Basic ZWxhc3RpYzp1MDFTM2NydEFCN3o='
  ```
  
