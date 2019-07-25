# Akka k8s cluster demo
This is simple Akka cluster demo tailored for deployment into kubernetes environment.

### Build and run
```
gradle clean installDist 
./build/install/akka-k8s-cluster/bin/akka-k8s-cluster
```
### Build Docker image 
```
docker build . -t akka-k8s-cluster:1.0.0-SNAPSHOT
docker image list
docker save --output="build/akka-k8s-cluster:1.0.0-SNAPSHOT.tar" akka-k8s-cluster:1.0.0-SNAPSHOT
docker image rm -f <imageid>
docker run -p 8558:8558 -p 2552:2552 akka-k8s-cluster:1.0.0-SNAPSHOT
```

## Akka management
Akka management endpoints:
* __GET__ http://127.0.0.1:8558/management/cluster/members
* __GET__ http://127.0.0.1:8558/management/bootstrap/seed-nodes
