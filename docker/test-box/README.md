# Test Box
This docker image is designed for testing and troubleshooting purposes.

## Tools installed
* telnet, nmap, ping, dig, nslookup, nc, tracepath
* wget, curl
* git
* java, maven
* terraform
* kubectl
* aws cli
* psql 
* redis-cli

## Run in docker 
```
docker build -t test-box:1.1.0 .
docker run --name test-box -d test-box:1.1.0
docker exec -ti test-box "/bin/bash"
docker stop test-box
docker rm test-box
```

## Publish to dockerhub
```
docker build -t jurajveverka/test-box:1.1.0 .
docker push jurajveverka/test-box:1.1.0
```

## Run in Kubernetes cluster
```
kubectl create deployment test-box -n <name-space> --image jurajveverka/test-box:1.1.0
kubectl exec -ti pod/test-box-<id> -n <name-space> /bin/bash
kubectl delete deployment test-box -n <name-space>
```
