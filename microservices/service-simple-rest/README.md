### Build and run
```
gradle clean build 
java -jar build/libs/service-simple-rest-1.0.0-SNAPSHOT.jar
```

### Build Docker image 
```
docker build . -t service-simple-rest:1.0.0-SNAPSHOT
docker image list
docker save --output="build/service-simple-rest:1.0.0-SNAPSHOT.tar" service-simple-rest:1.0.0-SNAPSHOT  
docker image rm -f <imageid>
docker run -p 8888:8080 service-simple-rest:1.0.0-SNAPSHOT
```

### REST endpoint
* __GET__ ``http://hostname:port/data/info``
