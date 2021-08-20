## Swarm Mode

### On Manager Node
```
docker swarm init --advertise-addr <MANAGER-IP>
```
### On Worker Nodes
```
docker swarm join --token <TOKEN> <MANAGER-IP>:2377
```

### Cheat Sheet
```
docker node ls
docker service ls
docker swarm join-token worker
```

### References
* [Swarm mode overview](https://docs.docker.com/engine/swarm/)
* [Swarm mode routing mesh](https://docs.docker.com/engine/swarm/ingress/)
* [Deploy to Swarm](https://docs.docker.com/get-started/swarm-deploy/)
