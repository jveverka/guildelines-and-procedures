FROM maven:3.8.2-amazoncorretto-11

LABEL org.opencontainers.image.authors="gergej123@gmail.com"

RUN yum -y install man vi vim git wget curl unzip telnet nmap iputils bind-utils postgresql net-tools
RUN wget https://releases.hashicorp.com/terraform/0.14.5/terraform_0.14.5_linux_amd64.zip && unzip terraform_0.14.5_linux_amd64.zip && chmod 755 terraform && mv terraform /bin/ && rm -f terraform_0.14.5_linux_amd64.zip
RUN wget https://storage.googleapis.com/kubernetes-release/release/v1.20.4/bin/linux/amd64/kubectl && chmod +x ./kubectl && mv kubectl /bin/
RUN wget https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip && unzip awscli-exe-linux-x86_64.zip && ln -s /aws/dist/aws /bin/aws

# install redis cli
RUN yum -y install gcc make openssl-devel
RUN wget http://download.redis.io/releases/redis-6.2.5.tar.gz && tar xzvf redis-6.2.5.tar.gz && cd redis-6.2.5 && make BUILD_TLS=yes

# install mongosh
RUN wget https://downloads.mongodb.com/compass/mongosh-1.0.5-linux-x64.tgz && tar xzvf mongosh-1.0.5-linux-x64.tgz

# install stress
RUN amazon-linux-extras install epel -y
RUN yum -y install stress

# install python3
RUN yum -y install python3

# install nginx
RUN yum -y install nginx

#RUN rm /etc/nginx/conf.d/default.conf
ADD default.config /etc/nginx/conf.d/default.conf

COPY README-docker.md /README.md
COPY loop.sh /loop.sh

EXPOSE 8080

ENTRYPOINT ["/loop.sh"]
