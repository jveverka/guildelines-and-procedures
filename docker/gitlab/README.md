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
docker stack deploy -c gitlab-docker-swarm.yml gitlab 
docker stack services gitlab
docker service ps --no-trunc gitlab_gitlab
docker service ps --no-trunc gitlab_gitlab-runner
````
3. Use gitlab stack
``http://<server-ip>`` root : topsecret
 
5. Remove gitlab stack
```
docker stack rm gitlab
rm -rf $GITLAB_HOME/data/*
rm -rf $GITLAB_HOME/data/.*
rm -rf $GITLAB_HOME/logs/*
rm -rf $GITLAB_HOME/config/*
``` 

### References
* [GitLab Docker Examples](https://github.com/docker-envs/gitlab)
* [GitLab Docker](https://docs.gitlab.com/ee/install/docker.html)
* [Gitlab Swarm](https://docs.gitlab.com/ee/install/docker.html#install-gitlab-using-docker-swarm-mode)
