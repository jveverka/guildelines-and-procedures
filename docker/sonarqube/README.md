# SonarQube

* Run with Docker compose
  ``` 
  docker-compose -f docker-compose.yml up -d
  ```
* Web UI is on address ``http://localhost:9000``
* Default login: admin / admin 

## Run maven 
```
mvn clean package sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=admin -Dsonar.password=Admin123;
```
