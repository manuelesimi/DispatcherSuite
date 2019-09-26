Dispatcher Microservice
----
A microservice project based on Spring Boot.

## Configuration
Mostly focused on the embedded Tomcat server, it is available in  _application.yml_:
```yaml
server:
    port: 8080
    servlet:
        context-path: /dispatcher
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

TBP

## Built With
* [Spring Boot](https://spring.io/projects/spring-boot) - A framework that makes it easy to create stand-alone, production-grade Spring-based Applications
* [OpenJDK](https://openjdk.java.net/) - A free and open-source implementation of the Java Platform, Standard Edition
* [Maven](https://maven.apache.org/) - For dependency management
