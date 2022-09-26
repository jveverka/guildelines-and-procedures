# Prometheus and Grafana 

* [installation](https://prometheus.io/docs/prometheus/latest/installation/)
* [configuration](https://prometheus.io/docs/prometheus/latest/configuration/configuration/)  
* [monitoring springboot](https://stackabuse.com/monitoring-spring-boot-apps-with-micrometer-prometheus-and-grafana/)

## Run in Docker
```
BASE_PATH=`pwd`
docker run \
    -d --name prometheus \
    --network host \
    -p 9090:9090 \
    -v ${BASE_PATH}/prometheus.yml:/etc/prometheus/prometheus.yml \
    prom/prometheus
```

```
docker run \
    -d --name grafana \
    --network host  \
    -p 3000:3000 grafana/grafana
```

## Run in docker-compose
```
docker-compose -f prometheus-grafana-compose.yml up -d
docker-compose -f prometheus-grafana-compose.yml down
```
## Check web UI
```
http://localhost:3000 (admin/admin)
http://localhost:9090
```

#### References
* [Docker Compose Stack](https://github.com/vegasbrianc/prometheus/blob/master/docker-compose.yml)
