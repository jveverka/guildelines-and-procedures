# Tensorflow, nVidia CUDA + cuDNN on Ubuntu 20.4
This guide describes Tensorflow, nVidia CUDA and cuDNN installation on Ubuntu 20.04.3 LTS.
* [Tensorflow 2.6.0](https://www.tensorflow.org/)
* [CUDA 11.4](https://developer.nvidia.com/cuda-downloads)
* [cuDNN 8](https://developer.nvidia.com/rdp/cudnn-download)

## Install Tensorflow 
1. Install Tensorflow dependencies
```
pip3 install --user --upgrade numpy
pip3 install --user --upgrade matplotlib
pip3 install --user --upgrade opencv-python
```
2. Install on system (no virtual env)
```
pip3 install --user --upgrade tensorflow
```
3. For GPU accelerated environments:
```
pip3 install --user --upgrade tensorflow-gpu
```

## Install CUDA and cuDNN
1. Activate NVIDIA Driver Version: 470.57.02

2. Install [CUDA Toolkit 11.4 Update 2 Downloads](https://developer.nvidia.com/cuda-downloads?target_os=Linux&target_arch=x86_64&Distribution=Ubuntu&target_version=20.04&target_type=deb_local).
```
wget https://developer.download.nvidia.com/compute/cuda/repos/ubuntu2004/x86_64/cuda-ubuntu2004.pin
sudo mv cuda-ubuntu2004.pin /etc/apt/preferences.d/cuda-repository-pin-600
wget https://developer.download.nvidia.com/compute/cuda/11.4.2/local_installers/cuda-repo-ubuntu2004-11-4-local_11.4.2-470.57.02-1_amd64.deb
sudo dpkg -i cuda-repo-ubuntu2004-11-4-local_11.4.2-470.57.02-1_amd64.deb
sudo apt-key add /var/cuda-repo-ubuntu2004-11-4-local/7fa2af80.pub
sudo apt-get update
sudo apt-get -y install cuda
```
3. Install cuDNN 8 packages `libcudnn8` and `libcudnn8-dev`. This step requires NVidia registration.
   Download packages [here](https://developer.nvidia.com/rdp/cudnn-download).
```
wget https://developer.nvidia.com/compute/machine-learning/cudnn/secure/8.2.4/11.4_20210831/Ubuntu20_04-x64/libcudnn8_8.2.4.15-1+cuda11.4_amd64.deb
wget https://developer.nvidia.com/compute/machine-learning/cudnn/secure/8.2.4/11.4_20210831/Ubuntu20_04-x64/libcudnn8-dev_8.2.4.15-1+cuda11.4_amd64.deb

sudo apt install ./libcudnn8_8.2.4.15-1+cuda11.4_amd64.deb
sudo apt install ./libcudnn8-dev_8.2.4.15-1+cuda11.4_amd64.deb
``` 

## Test Installation
```python
import tensorflow as tf
if tf.test.gpu_device_name():
    print('Default GPU Device: {}'.format(tf.test.gpu_device_name()))
else:
    print("Please install GPU version of TF")
```

### Useful commands
```
nvidia-smi
nvtop
```

### References
* [NVIDIA CUDA Docker](https://hub.docker.com/r/nvidia/cuda)
* [TensorFlow Docker](https://hub.docker.com/r/tensorflow/tensorflow/)