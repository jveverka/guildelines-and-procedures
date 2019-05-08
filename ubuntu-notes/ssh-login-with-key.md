# SSH login with key
This desctibes how to setup server to login via ssh with key (without password.)
First generate key 
```
ssh-keygen -f controlkey
mv controlkey controlkey.pri
```

* __controlkey.pri__ - private key
* __controlkey.pub__ - public key

Copy public key to target remote server.
```
scp controlkey.pub user@targetserver:/home/user/.ssh/
```
On remote server, set correct acces mode for ``controlkey.pub``
```
mkdir /home/user/.ssh 
chmod 700 /home/user/.ssh
cd /home/user/.ssh
cat controlkey.pub >> authorized_keys
chmod 600 authorized_keys
rm controlkey.pub
```
On local server, login (or execute command) to remote like this:
```
ssh -i controlkey.pri user@targetserver
ssh -i controlkey.pri user@targetserver ls -la 
```

