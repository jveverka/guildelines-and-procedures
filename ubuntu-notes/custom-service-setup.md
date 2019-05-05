# Custom service setup

## Create start and stop script
Write start and stop script for your service. Some general rules:
* scripts will run under ubuntu user (or non-root user)
* make sure all paths in scripts are absolute (if relative, make sure are valid)
* make sure start script does not terminate

## Create service descriptor
Create ``myservice.service`` file with following content:
```
[Unit]
Description=My Service
After=network.target
After=systemd-user-sessions.service
After=network-online.target

[Service]
User=ubuntu
Type=simple
WorkingDirectory=/opt/service
ExecStart=/opt/service/service-start.sh
ExecStop=/opt/service/service-stop.sh
TimeoutSec=30
Restart=on-failure
RestartSec=30
StartLimitInterval=350
StartLimitBurst=10

[Install]
WantedBy=multi-user.target
```

## Install and activate service
```
sudo mv myservice.service /etc/systemd/system/
sudo chown root:root /etc/systemd/system/myservice.service
sudo systemctl daemon-reload
sudo systemctl enable myservice
```

## Start, Stop, Status
```
sudo systemctl start myservice
sudo systemctl stop myservice
sudo systemctl status myservice
```

