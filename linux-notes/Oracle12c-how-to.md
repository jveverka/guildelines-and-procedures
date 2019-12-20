## Install OS
Install [centos6](http://linux.mirrors.es.net/centos/6.10/isos/x86_64/), unfortunately Oracle provide installer with UI, 
so it will be easier to use VM with UI. Use 40GB disk size, 4GB RAM and 1 CPU. Swap size is 4GB or more.
```
yum update
yum install zip unzip wget curl vim
```

## Add Oracle repository
Based on [this](https://www.oracle.com/technetwork/articles/servers-storage-admin/ginnydbinstallonlinux-488779.html) article.
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
```
yum install oracle-rdbms-server-12cR1-preinstall
```
Download database installation binary ``linuxx64_12201_database.zip``, extract this file into ``/opt`` directory.
Strart oracle intaller.
```
cd /opt/database/
./runInstaller 
# follow installer instructions
```
