# Install PostgeSQL 9.5 on Ubuntu 18.04 LTS
Normally Ubuntu 18.04 LTS comes with postgresql 10. This is how you can install
older version 9.5 of PostgeSQL.

1. Download deb packages
   ```
   wget http://security.ubuntu.com/ubuntu/pool/main/p/postgresql-9.5/postgresql-client-9.5_9.5.19-0ubuntu0.16.04.1_amd64.deb
   wget http://security.ubuntu.com/ubuntu/pool/main/p/postgresql-9.5/postgresql-9.5_9.5.19-0ubuntu0.16.04.1_amd64.deb 
   ``` 
2. Install dependencies and packages.
   ```
   sudo apt install libedit2 libpq5 libssl1.0.0 postgresql-client-common zlib1g libgssapi-krb5-2 libldap-2.4-2 libpam0g libxml2 postgresql-common locales ssl-cert tzdata sysstat  
   sudo dpkg -i postgresql-client-9.5_9.5.19-0ubuntu0.16.04.1_amd64.deb postgresql-9.5_9.5.19-0ubuntu0.16.04.1_amd64.deb
   ```
3. Configure PostgeSQL server.
4. Start / Stop database server.
   