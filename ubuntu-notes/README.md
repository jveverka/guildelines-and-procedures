### Edit application launcher 
```cd .local/share/applications/```
edit application launcher, for example:
```vi jetbrains-idea-ce.desktop```

### mount drive over ssh
```
sudo apt-get install sshfs
sudo sshfs -o allow_other,defer_permissions root@192.168.0.249:/opt/ /mnt/remote-ssh/
```


