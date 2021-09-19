# IBM DB2 in docker

* [Simple testcontainers/jdbc DB2 example](db2-test). 

```
cat .env_list
LICENSE=accept
DB2INSTANCE=db2inst1
DB2INST1_PASSWORD=password
DBNAME=testdb
BLU=false
ENABLE_ORACLE_COMPATIBILITY=false
UPDATEAVAIL=NO
TO_CREATE_SAMPLEDB=false
REPODB=false

docker run -h dbsrv --name dbsrv --restart=always --detach --privileged=true --env-file .env_list ibmcom/db2
```

### References 
* [Official Dockerhub Image](https://hub.docker.com/r/ibmcom/db2)
* [testcontainers.org](https://www.testcontainers.org/modules/databases/db2/)
