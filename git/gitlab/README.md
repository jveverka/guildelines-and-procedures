
https://hub.docker.com/r/gitlab/gitlab-ce/
https://hub.docker.com/r/gitlab/gitlab-ee/

sudo docker run --detach \
  --hostname 192.168.0.1 \
  --publish 443:443 --publish 80:80 --publish 22:22 \
  --name gitlab \
  --restart always \
  --volume $GITLAB_HOME/config:/etc/gitlab \
  --volume $GITLAB_HOME/logs:/var/log/gitlab \
  --volume $GITLAB_HOME/data:/var/opt/gitlab \
  gitlab/gitlab-ee:latest

