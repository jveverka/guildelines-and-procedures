# Apache Kafka

## Swarm stack
```shell
docker stack deploy -c kafka-docker-swarm.yml kafka
docker stack rm kafka 
```

## Monitoring
```
docker run -p 8081:8080 -e KAFKA_BROKERS=localhost:9092 quay.io/cloudhut/kowl:master
```
http://localhost:8081/
 
