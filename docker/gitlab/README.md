# Gitlab in docker
1. Prepare environment
```
export GITLAB_HOME=/path/to/gitlab/home
mkdir -p $GITLAB_HOME/data
mkdir -p $GITLAB_HOME/logs
mkdir -p $GITLAB_HOME/config
```
2. Start gitlab stack
````
docker stack deploy -c gl-docker-swarm.yml gl-stack 
docker stack services gl-stack
docker service ps --no-trunc <service-name>
````
3. Remove gitlab stack
```
docker stack rm gl-stack
``` 

### References
* [GitLab Docker](https://docs.gitlab.com/ee/install/docker.html)
* [Gitlab Swarm](https://docs.gitlab.com/ee/install/docker.html#install-gitlab-using-docker-swarm-mode)
