# sample-akka-http-docker

An Sample application [Scala](scala-lang.org) that leverages  and [Akka](akka.io) 
[sbt-native-packager](https://github.com/sbt/sbt-native-packager) which is built using [Spotify docker client integration]) libs.

An Sample application that leverages [Akka-http](akka.io) and [Spray](spray.io) to expose Rest API. 
Description is exposed as Swagger Json page.
 
 
### Running
Clone to your computer and run via sbt:

```
sbt run
```

Generates a directory with the Dockerfile and environment prepared for creating a Docker image.
```
docker:stage
```

Builds an image using the local Docker server.
```
docker:publishLocal
```

Builds an image using the local Docker server, and pushes it to the configured remote repository.
```
docker:publish
```

Removes the built image from the local Docker server.
```
docker:clean
```

To run image and bind to host MongoDB port (27017) and Http interface :

```
$ docker run \
        --name newsbridge-sample-akka-http-docker \
        -p 8080:8080 \
        newsbridge/sample-akka-http-docker:latest
```

Test with httpie
```
 http localhost:8080/hello/flore
```


## Option push to ECS
```
aws ecr get-login --region eu-west-1 
docker tag newsbridge/sample-akka-http-docker:latest ############.dkr.ecr.eu-west-1.amazonaws.com/sample-akka-http-docker:latest 
docker push ############.dkr.ecr.us-west-2.amazonaws.com/sample-akka-http-docker:latest
```


### License
This library is licensed under the Apache License, Version 2.0.
