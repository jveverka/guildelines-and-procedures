# Git guidelines

* [How to setup simple Git server](https://git-scm.com/book/en/v2/Git-on-the-Server-Setting-Up-the-Server)
* [gitlab](gitlab)
* [gerrit](gerrit)
* [github](github)

### git branch diff
```
git log --oneline origin/<branch-name-01>..origin/<branch-name-02>
```

### Many ssh keys for different git servers
1. Generate ssh keypair 1  
   ```
   cd ~/.ssh/
   ssh-keygen -t rsa -f id_rsa_id1
   chmod 400 id_rsa_id1*
   ```
2. Generate ssh keypair 1
   ```
   cd ~/.ssh/
   ssh-keygen -t rsa -f id_rsa_id1
   chmod 400 id_rsa_id1*
   ```
3. Create ~/.ssh/config file
   ```
   #1 server
   Host github.com
           HostName github.com
           User user1
           IdentityFile ~/.ssh/id_rsa_id1
           IdentitiesOnly yes

   #2 server  
   Host gitlab.com
           HostName gitlab.com
           User user2
           IdentityFile ~/.ssh/id_rsa_id2
           IdentitiesOnly yes
   ``` 
4. Upload public keys to corresponding servers.   
5. Other ssh commands
   ```
   ssh-add -l
   
   ```