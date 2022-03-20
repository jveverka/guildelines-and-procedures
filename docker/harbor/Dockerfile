FROM ubuntu:20.04

RUN apt update
RUN apt upgrade -y
RUN apt install wget docker.io docker-compose sudo systemctl -y
RUN wget https://github.com/goharbor/harbor/releases/download/v1.10.10/harbor-offline-installer-v1.10.10.tgz
RUN tar xzvf harbor-offline-installer-v1.10.10.tgz
RUN mkdir /datap
COPY harbor.yml /harbor.yml

#RUN sudo systemctl enable --now docker
#RUN sudo systemctl start docker
#RUN cd harbor && ./install.sh
