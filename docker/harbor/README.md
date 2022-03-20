# GoHarbor.io

```
docker build -t harbor:1.0.0 .
docker run -it \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v /var/lib/docker/volumes:/var/lib/docker/volumes \
  harbor:1.0.0 /bin/bash
```

### References
* [goharbor.io](https://goharbor.io/)
