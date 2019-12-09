# AWS Proxy Server
This guide describes how to use public AWS server as reverse proxy for 
exposing private REST services running on localhost. Used techniques:
* [ssh remote port forwarding](https://help.ubuntu.com/community/SSH/OpenSSH/PortForwarding#Remote_Port_Forwarding)
* [nginx reverse proxy](https://docs.nginx.com/nginx/admin-guide/web-server/reverse-proxy/)

## Setup (who-is-who)
1. [D] Developer's local machine
2. [A] AWs proxy server with public IP and DNS [aws-public-dns]

__Use Case:__ I as developer want to expose my local REST service under public DNS.

## Setup AWS server [A]
1. Create EC2 instance of Ubuntu server 18.04 LTS (t2.micro)
2. Enable port ``8080`` in security group used.
3. Install [nginx](https://www.nginx.com/) proxy.
   ```
   sudo apt install nginx
   ```
4. Set nginx reverse proxy by editing ``/etc/nginx/sites-enabled/default`` 
   ```
   server {
	  listen 8080 default_server;

      location / {
         proxy_pass http://localhost:5001;
         proxy_http_version 1.1;
         proxy_set_header Upgrade $http_upgrade;
         proxy_set_header Connection 'upgrade';
         proxy_set_header Host $host;
         proxy_cache_bypass $http_upgrade;
      }
      server_name _;
   }
   ```
5. Start nginx reverse proxy as deamon, leave it running.
   ```
   sudo systemctl start nginx 
   ```   

## On Developer's machine [D]
1. Start local REST service on port 8080
2. Open remote ssh tunnel from [D] -> [A]
   ``ssh -i aws-key.pem -R 5001:127.0.0.1:8080 ubuntu@aws-public-dns``

## Test whole setup
To test if developer's REST service is publicly available at 
``http://aws-public-dns:8080/`` use web browser, wget or curl.