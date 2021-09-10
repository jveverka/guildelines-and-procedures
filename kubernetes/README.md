# Install Kubernetes on [Ubuntu 20.04](https://releases.ubuntu.com/20.04.3/ubuntu-20.04.3-live-server-amd64.iso)
## On All nodes
1. [Install Docker](docker/README.md)
2. Disable swap
3. Configure the Docker daemon, in particular to use systemd for the management of the container’s cgroups.
   [details](https://kubernetes.io/docs/setup/production-environment/container-runtimes/#docker) 
   ```
   sudo mkdir /etc/docker
   cat <<EOF | sudo tee /etc/docker/daemon.json
   {
     "exec-opts": ["native.cgroupdriver=systemd"],
     "log-driver": "json-file",
     "log-opts": {
        "max-size": "100m"
     },
     "storage-driver": "overlay2"
   }
   EOF
   ``` 
4. Restart docker 
   ```
   sudo systemctl restart docker
   ```
5. [Install kubeadm](https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/install-kubeadm/)   
   ```
   sudo apt-get update
   sudo apt-get install -y apt-transport-https ca-certificates curl
   sudo curl -fsSLo /usr/share/keyrings/kubernetes-archive-keyring.gpg https://packages.cloud.google.com/apt/doc/apt-key.gpg
   echo "deb [signed-by=/usr/share/keyrings/kubernetes-archive-keyring.gpg] https://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee /etc/apt/sources.list.d/kubernetes.list
   sudo apt-get update
   sudo apt-get install -y kubelet kubeadm kubectl
   sudo apt-mark hold kubelet kubeadm kubectl
   ```

## On Kubernetes Master
1. Init kubernetes master
   ```
   sudo kubeadm init --pod-network-cidr=10.244.0.0/16 --apiserver-advertise-address=<k8s-master-ip>
   ``` 
2. Finish setup as regular user.
   ```
   mkdir -p $HOME/.kube
   sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
   sudo chown $(id -u):$(id -g) $HOME/.kube/config
   ```
3. Setup cluster network (flannel).
   ```
   kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml; 
   ```

## On all Kubernetes workers
```
sudo kubeadm join <k8s-master-ip>:6443 --token <token> \
	--discovery-token-ca-cert-hash sha256:<token-hash>
```

## Check cluster health
```
kubectl get nodes
kubectl get -A
```