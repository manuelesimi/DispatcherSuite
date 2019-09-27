Dispatcher Microservice
----
A microservice project based on Spring Boot.

## Configuration
For the embedded Tomcat server, it is available in  _application.yml_:
```yaml
server:
    port: 8080
    servlet:
        context-path: /dispatcher
```

For the microservice logic:
```yaml
actions:
-
  topic: topicA
  messages:
    - message: messageA.1
      trigger: nextflow main.nf --some parameters = values
    - message: messageA.2
      trigger: https://mymicroservice/name/api?param=value&param2=value2
    - message: messageA.3
      trigger: whatever you want
-
  topic: topicB
  messages:
    - message: messageB.1
      trigger: command/URL for B.1
    - message: messageB.2
      trigger: command/URL for B.2
    - message: messageB.3
      trigger: command/URL for B.3

-
  topic: topicC
  messages:
    - message: messageC.1
      trigger: command/URL for C.1
    - message: messageC.2
      trigger: command/URL for C.2
    - message: messageC.3
      trigger: command/URL for C.3

```

## Building and Packaging
~~~
./mvnw clean package
~~~
## Activation with the embedded Tomcat server
~~~
./mvnw spring-boot:run
~~~
![Emb start](doc/EmbTomcatStart.png)
## Interface

Sample invocations:
~~~
http://localhost:8080/dispatcher/ (welcome message)
http://localhost:8080/dispatcher/configuration (shows the entire configuration)
http://localhost:8080/dispatcher/configuration/topics (shows all topics of interest)
http://localhost:8080/dispatcher/configuration/topic?name=topicA (shows all messages of interest for the topic)
http://localhost:8080/dispatcher/dispatch?topic=topicA&message=messageA.2 (simulate a message, shows the trigger)
~~~

## Built With
* [Spring Boot](https://spring.io/projects/spring-boot) - A framework that makes it easy to create stand-alone, production-grade Spring-based Applications
* [OpenJDK](https://openjdk.java.net/) - A free and open-source implementation of the Java Platform, Standard Edition
* [Maven](https://maven.apache.org/) - For dependency management
