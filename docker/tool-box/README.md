# Tool-Box
This docker image is designed for testing and troubleshooting purposes.

## Tools installed
* telnet, nmap, ping, dig, nslookup, nc, tracepath, stress
* wget, curl
* git
* java, maven
* terraform
* kubectl
* aws cli
* psql 
* redis-cli
* mongosh
* python3

## Run in docker 
```
docker build -t jurajveverka/tool-box:1.3.0 .
docker run --name tool-box -d jurajveverka/tool-box:1.3.0
docker exec -ti tool-box "/bin/bash"
docker stop tool-box
docker rm tool-box
```

## Publish to dockerhub
```
docker build -t jurajveverka/tool-box:1.3.0 .
docker push jurajveverka/tool-box:1.3.0
```

## Run in Kubernetes cluster
```
kubectl create deployment tool-box -n <name-space> --image jurajveverka/tool-box:1.3.0
kubectl exec -ti pod/tool-box-<id> -n <name-space> /bin/bash
kubectl delete deployment tool-box -n <name-space>
```
