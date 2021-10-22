# portainer.io
[portainer.io](https://www.portainer.io/)

## Install Server
```
docker stack deploy -c portainer-stack.yml portainer 
```

## Install Agents
```
docker stack deploy -c portainer-agent-stack.yml portainer-agent 
```

### References
* [Swarm Install](https://documentation.portainer.io/v2.0/deploy/ceinstallswarm/)
* [Agent Manual](https://downloads.portainer.io/edge_agent_guide.pdf)