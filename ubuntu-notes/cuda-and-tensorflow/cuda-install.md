# CUDA and cuDNN on Ubuntu 20.4
This guide describes NVidia cuda and cuDNN installation on Ubuntu 20.04.3 LTS.

0. Activate NVIDIA Driver Version: 470.57.02

1. Install [CUDA Toolkit 11.4 Update 2 Downloads](https://developer.nvidia.com/cuda-downloads?target_os=Linux&target_arch=x86_64&Distribution=Ubuntu&target_version=20.04&target_type=deb_local).
```
wget https://developer.download.nvidia.com/compute/cuda/repos/ubuntu2004/x86_64/cuda-ubuntu2004.pin
sudo mv cuda-ubuntu2004.pin /etc/apt/preferences.d/cuda-repository-pin-600
wget https://developer.download.nvidia.com/compute/cuda/11.4.2/local_installers/cuda-repo-ubuntu2004-11-4-local_11.4.2-470.57.02-1_amd64.deb
sudo dpkg -i cuda-repo-ubuntu2004-11-4-local_11.4.2-470.57.02-1_amd64.deb
sudo apt-key add /var/cuda-repo-ubuntu2004-11-4-local/7fa2af80.pub
sudo apt-get update
sudo apt-get -y install cuda
```
2. Install cuDNN 8 packages `libcudnn8` and `libcudnn8-dev`. This step requires NVidia registration.
   Download packages [here](https://developer.nvidia.com/rdp/cudnn-download).
```
wget https://developer.nvidia.com/compute/machine-learning/cudnn/secure/8.2.4/11.4_20210831/Ubuntu20_04-x64/libcudnn8_8.2.4.15-1+cuda11.4_amd64.deb
wget https://developer.nvidia.com/compute/machine-learning/cudnn/secure/8.2.4/11.4_20210831/Ubuntu20_04-x64/libcudnn8-dev_8.2.4.15-1+cuda11.4_amd64.deb

sudo apt install ./libcudnn8_8.2.4.15-1+cuda11.4_amd64.deb
sudo apt install ./libcudnn8-dev_8.2.4.15-1+cuda11.4_amd64.deb
``` 

### Useful commands
```
nvidia-smi
nvtop
```