# Oracle XE HOW-TO
This is simple guide for [Oracle XE](https://www.oracle.com/database/technologies/appdev/xe.html) installation and 
configuration on [CentOS 7](https://wiki.centos.org/Manuals/ReleaseNotes/CentOS7.1908?action=show&redirect=Manuals%2FReleaseNotes%2FCentOS7).

## Setup CentOS 7
* Disable firewall
  ```
  systemctl status firewalld
  systemctl stop firewalld
  systemctl disable firewalld
  ```

## Install Oracle XE
* Download [oracle-database-xe-18c-1.0-1.x86_64.rpm](https://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/)
* Download and install server. 
  ```
  curl -o oracle-database-preinstall-18c-1.0-1.el7.x86_64.rpm https://yum.oracle.com/repo/OracleLinux/OL7/latest/x86_64/getPackage/oracle-database-preinstall-18c-1.0-1.el7.x86_64.rpm
  yum -y localinstall oracle-database*18c*
  /etc/init.d/oracle-xe-18c configure
  ```
* Enable autostart after system reboot.
  ```
  systemctl daemon-reload
  systemctl enable oracle-xe-18c  
  ```  
* Set environment variables
  ```
  export ORACLE_SID=XE
  export ORAENV_ASK=NO
  . /opt/oracle/product/18c/dbhomeXE/bin/oraenv
  #export PATH=/opt/oracle/product/18c/dbhomeXE/bin:$PATH
  #export ORACLE_HOME=/opt/oracle/product/18c/dbhomeXE
  ```
* Bind service to 0.0.0.0 
  ```
  sqlplus system
  Enter password: SYSTEM_password
  SQL> EXEC DBMS_XDB.SETLISTENERLOCALACCESS(FALSE);
  ```  
  ```
  lsnrctl stop
  edit $ORACLE_HOME/network/admin/listener.ora #set HOST = 192.168.99.101
  edit $ORACLE_HOME/network/admin/tnsnames.ora #set HOST = 192.168.99.101
  # restart server
  lsnrctl start
  netstat -vatn
  ```

## Install SQL developer
To manage DBs, use [SQL developer](https://www.oracle.com/tools/downloads/sqldev-v192-downloads.html).
Install JVM and start SQL Developer.

## DB server Start, Stop, Restart
```
systemctl status oracle-xe-18c
systemctl start oracle-xe-18c
systemctl stop oracle-xe-18c
```

## Test local connection
```
sqlplus system/secret@192.168.99.101:1521
```

