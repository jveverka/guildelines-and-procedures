### Docker on SE Linux Host
1. Install and enable selinux on docker host.
   ```
   sudo apt install policycoreutils selinux-utils selinux-basics
   sudo selinux-activate
   sudo selinux-config-enforcing
   sudo sestatus
   ```
2. On Docker host: into docker config file ``/etc/docker/daemon.json``, add ``"selinux-enabled": true``, restart docker daemon.
3. Verify if selinux is active with docker daemon.
   ```
   docker info | grep Security -A3
   ```

### References
* [Secure your containers with SELinux](https://opensource.com/article/20/11/selinux-containers)
