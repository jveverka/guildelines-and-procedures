# Prometheus and Grafana 

* [installation](https://prometheus.io/docs/prometheus/latest/installation/)
* [configuration](https://prometheus.io/docs/prometheus/latest/configuration/configuration/)  
* [monitoring springboot](https://stackabuse.com/monitoring-spring-boot-apps-with-micrometer-prometheus-and-grafana/)

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
