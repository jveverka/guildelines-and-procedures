## Install OS
Install [centos6](http://linux.mirrors.es.net/centos/6.10/isos/x86_64/), unfortunately Oracle provide installer with UI, 
so it will be easier to use VM with UI. Use 40GB disk size, 4GB RAM and 1 CPU. 
* Swap size must be 4GB or more.
* Install with profile ``Minimal Desktop``
* Create user ``oracle`` and add this user to ``/etc/sudoers``
* Update installation and install additional packages.
  ```
  yum update
  yum install zip unzip wget curl vim
  ```
* disable firewall, run as root ``system-config-firewall-tui``

## Add Oracle repository
Based on [this](https://www.oracle.com/technetwork/articles/servers-storage-admin/ginnydbinstallonlinux-488779.html) article.
Run as root or use ``sudo``.
```
cd /etc/yum.repos.d
wget http://yum.oracle.com/public-yum-ol6.repo
cd /etc/pki/rpm-gpg
wget https://oss.oracle.com/ol6/RPM-GPG-KEY-oracle
```

Oracle's Public Key content:
```
-----BEGIN PGP PUBLIC KEY BLOCK-----
Version: GnuPG v1.4.5 (GNU/Linux)

mQENBEwtJWoBCACpiY2rGA6T0ceBi92X88/QclytVBjtDRohOVzs3pmIPh3ULqsW
G323nmyKbKQBBSjh9OnuO9Y09VG8mzr/w9YV0Ix4cI9/HDTERZ2+TR5u+VNn5J5h
yvwQSN/FEK6oH2+mz7O0yUNleN7UltR4MOEkHIoAhIvv+1AQQKN3OM8oalz+3gv/
zz9rAoQczQzT7QWOtBrsRMZgBrKXY/TpJrpVSO3Hx8CnbhKGtLxCCrxZ5v7hh1ht
3CRAr2+h5bDA9KP6vBZWKEs7NgcvtZFDY6EJc7AoApr3phX9hHE2+snTxe82DkFT
uA69C8wLyjPCoSy+tcaCqJKOZelNy9RN/WKRABEBAAG0RE9yYWNsZSBPU1MgZ3Jv
dXAgKE9wZW4gU291cmNlIFNvZnR3YXJlIGdyb3VwKSA8YnVpbGRAb3NzLm9yYWNs
ZS5jb20+iQE8BBMBAgAmBQJMLSVqAhsDBQkWjmoABgsJCAcDAgQVAggDBBYCAwEC
HgECF4AACgkQcvl7dOxVHwMiNAf/cD8R74fCBeQsAYid5slIz7CG8xEOBUTDNEJT
p/owtzr7m7Ydp1txNBOkVeVkUP8czX5EldcmoxA4kCCyHhnxmpJnOt52Fy5ZRnYh
Ll5gYdpJpXGVScB7fnlh3rJAaesSTacVFC5MKIYPZBiTo9soSXMLNcG8WqHPasdd
AblC4t5BTDNYlX1RiPeP6m5egHnnxyAXsis8fqIZY0RC9hERxWQ6hdDAX0tJXY8F
88HDUozvo8jqTlg/5GZcfqcbUjjMUJQ5cBtH3adCthMycdPpPXWJQBuzMIdFJ03u
YuQ3XrKxBkOLips+OZuWNVZzrPOHsenb49aX4yQsLVc2E2fhKQ==
=M8XY
-----END PGP PUBLIC KEY BLOCK-----
```

## Install Oracle DB
* Install dependencies and prerequisities
  ```
  sudo yum install ksh gcc libaio-devel gcc-c++ compat-libcap1 compat-libstdc++-33
  sudo yum install oracle-rdbms-server-12cR1-preinstall
  ```
* Download database installation binary [linuxx64_12201_database.zip](https://www.oracle.com/database/technologies/oracle12c-linux-12201-downloads.html), into ``/opt``.
* extract installation binary file in ``/opt`` directory.
  ```
  sudo unzip linuxx64_12201_database.zip
  ```
* As user ``oracle`` start oracle intaller. 
  ```
  cd /opt/database/
  ./runInstaller 
  # follow installer instructions
  ```
  * leave unchecked "I wish to receive security ..."
  * enter email - any email may  not be valid or press escape. 
  * System Class - Desktop Class
  * Use default values suggested by installer.
* After installation has completed try [web ui](https://localhost:5500/em).
* In case of re-installation:
  ```
  rm -rf /home/oracle/app/oraInventory
  ```

## Post-Install setup
* As a root user: Change the ``/etc/oratab`` entry from  “N” to  “Y”
  ```
  orcl:/home/oracle/app/oracle/product/12.2.0/dbhome_1:Y
  ```
* set environment variables for ``oracle`` user in ``.bash_profile``
  ```
  export ORACLE_HOSTNAME=localhost.localdomain
  export ORACLE_UNQNAME=orcl
  export ORACLE_BASE=/home/oracle/app/oracle
  export ORACLE_HOME=$ORACLE_BASE/product/12.2.0/dbhome_1
  export ORACLE_SID=orcl

  export PATH=$ORACLE_BASE/product/12.2.0/dbhome_1/bin:$PATH  
  ```
* set listening IP address 
  edit files 
  * ``/home/oracle/app/oracle/product/12.2.0/dbhome_1/network/admin/listener.ora``
  * ``/home/oracle/app/oracle/product/12.2.0/dbhome_1/network/admin/tnsnames.ora``
  set ``HOST = 0.0.0.0``
* start database as ``oracle`` user
  ```
  cd /home/oracle/app/oracle/product/12.2.0/dbhome_1/bin/
  ./dbstart
  ./lsnrctl start
  ```  
* Test database connection 
  * In __Oracle SQL developer__ create connection: 
    ```
	Username: SYS
	Password: Secret123
	Role: SYSDBA
	Hostname: 192.168.44.215
	Port: 1521
	SID: orcl
	```

## Set Oracle DB to start automatically
* create init script ``/etc/init.d/oracledb``
  ```
  #!/bin/bash
  #
  # Invokes the oracledb start / stop procedure 
  #
  # chkconfig: 2345 99 95
  #
  # description:Invokes the oracledb start / stop procedure 
  #

  . /etc/init.d/functions

  export ORACLE_HOME=/home/oracle/app/oracle/product/12.2.0/dbhome_1

  case "$1" in
   start)
     echo "Starting Oracle: "
     su oracle -c "$ORACLE_HOME/bin/dbstart $ORACLE_HOME" &
     su oracle -c "$ORACLE_HOME/bin/lsnrctl start" &
     RETVAL=$?
     ;;
   stop)
     echo "Stopping Oracle: "
     su oracle -c "$ORACLE_HOME/bin/lsnrctl stop" &
     su oracle -c "$ORACLE_HOME/bin/dbshut $ORACLE_HOME" &
     RETVAL=$?
     ;;
   status)
     PID_LISTENER=`ps -ef | grep oracle | grep tnslsnr | awk '{ print $2 }'`
     PID_ORACLE=`ps -ef | grep oracle | grep ora_pmon_orcl | awk '{ print $2 }'`
     echo "PID_LISTENER=${PID_LISTENER}"
     echo "PID_ORACLE=${PID_ORACLE}"
     RETVAL=0
     ;;
   *)
     echo $"Usage: $0 {start|stop|status}"
     RETVAL=1
  esac

  exit $RETVAL
  ```
* enable init script
  ```
  chkconfig --add oracledb
  chkconfig --list oracledb
  ```
* start and stop database server
  ```
  service oracledb start
  service oracledb stop
  service oracledb status
  ```

## Tips and Tricks
* run sql script using CLI:
  ``sqlplus -S SYSTEM/*******@192.168.44.215:1521/orcl @test.sql``
  Script content:
  ```
  SELECT * FROM v$version;
  quit;
  /
  ```  
  
### References
* [Install Oracle 12c on Centos7](https://www.tecmint.com/install-oracle-database-12c-on-centos-7/).
  
