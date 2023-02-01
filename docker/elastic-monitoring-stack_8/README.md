# Elastic - Kibana stack
Based on [official documentation](https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html#_start_your_cluster_with_security_enabled_and_configured).

## Prerequisites
```shell
docker-compose -f ek-docker-compose-1n.yml up -d
```

## Run 1-Node stack in Docker compose
```
docker-compose -f ek-docker-compose-1n.yml up -d
docker-compose -f ek-docker-compose-1n.yml down -v 
docker-compose -f ek-docker-compose-1n.yml down -v --rmi all --remove-orphans
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
  