# Prometheus  

* [installation](https://prometheus.io/docs/prometheus/latest/installation/)
* [configuration](https://prometheus.io/docs/prometheus/latest/configuration/configuration/)  
* [monitoring springboot](https://stackabuse.com/monitoring-spring-boot-apps-with-micrometer-prometheus-and-grafana/)

```
docker run \
    -p 9090:9090 \
    -v /path/to/prometheus.yml:/etc/prometheus/prometheus.yml \
    prom/prometheus
```