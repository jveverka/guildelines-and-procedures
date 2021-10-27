# Gitlab in docker
1. Start gitlab stack
````
docker stack deploy -c gitlab-docker-swarm.yml gitlab 
docker stack services gitlab
docker service ps --no-trunc gitlab_gitlab
docker service ps --no-trunc gitlab_gitlab-runner
````
2. Use gitlab stack
``http://<server-ip>`` root : topsecret
 
3. Remove gitlab stack
```
docker stack rm gitlab
rm -rf $GITLAB_HOME/data/*
rm -rf $GITLAB_HOME/data/.*
rm -rf $GITLAB_HOME/logs/*
rm -rf $GITLAB_HOME/config/*
``` 
4. Check volumes in use
```
docker volume ls
docker volume inspect <volume>
``` 

## Setup Runner in Docker

## Install and Setup Runner on VM
1. Make sure the docker is installed.  
2. Install gitlab runner.
```
sudo yum update -y
sudo yum install -y git
curl -LJO "https://gitlab-runner-downloads.s3.amazonaws.com/v14.3.2/rpm/gitlab-runner_amd64.rpm"
rpm -i gitlab-runner_amd64.rpm
rpm -e gitlab-runner
```

### References
* [GitLab Docker Examples](https://github.com/docker-envs/gitlab)
* [GitLab Docker](https://docs.gitlab.com/ee/install/docker.html)
* [Gitlab Swarm](https://docs.gitlab.com/ee/install/docker.html#install-gitlab-using-docker-swarm-mode)
