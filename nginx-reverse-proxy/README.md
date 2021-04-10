# Using NGINX as Reverse Proxy  

This simple setup shows how to run Angular UI and java web backend behind
NGINX configured as reverse proxy.

![architecture](reverse-proxy.svg)

## Setup
- web browser is talking to single server without knowing that UI and REST services are running on separate servers.
- CORS is not necessary
- nginx configuration is [here](default) 
- nginx is routing http traffic to angular UI and/or REST server

## Install nginx
[nginx](https://www.nginx.com/) installation (for ubuntu linux 20.04 LTS)
```
sudo apt install nginx
sudo systemctl start nginx 
sudo systemctl stop nginx 
sudo systemctl restart nginx
```
config: ``/etc/nginx/sites-enabled/default``
