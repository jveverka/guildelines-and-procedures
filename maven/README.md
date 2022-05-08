# Maven

### Download Artefact fo local cache
```
mvn org.apache.maven.plugins:maven-dependency-plugin:3.3.0:get \
    -DremoteRepositories=url \
    -Dartifact=groupId:artifactId:version
```
