# Jfrog Artifactory

```
mkdir -p /mnt/data/artifactory/postgresql
mkdir -p /mnt/data/artifactory/artifactory
docker stack deploy -c artifactory-postgresql.yml artifactory
docker stack rm artifactory
```

### References
* [Jfrog Artifactory](https://jfrog.com/artifactory/)
* [artifactory-docker-examples](https://github.com/jfrog/artifactory-docker-examples)