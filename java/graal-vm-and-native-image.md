## Install Graal VM and native-image tool
This procedure is valid for Ubuntu 20.04 amd64 and arm64v8 build servers.
```
sudo apt install zip unzip curl
sudo apt install build-essential libz-dev zlib1g-dev
curl -s "https://get.sdkman.io" | bash
sdk install java 21.3.0.r17-grl
sdk install gradle 7.3.3
gu install native-image
```