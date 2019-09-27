Dispatcher Microservice
----
A microservice project based on Spring Boot.

## Configuration
### For the embedded Tomcat server
the configuration is available in  _application.yml_ inside the app distribution:
```yaml
server:
    port: 8080
    servlet:
        context-path: /dispatcher
```

### For the application logic:
For each message under the topic of interest, the Dispatcher launches the associated trigger. The following sample instance shows the expected format of this part of the configuration:

```yaml
actions:
- topic: seq_done
  messages:
    - message: sample_ready
      trigger: nextflow main.nf --sampleID=${payload}
    - message: sample_failed
      trigger: https://mymicroservice/name/api?param=value&sampleID=${payload}
    - message: sample_processed
      trigger: whatever you want with ${payload}
- topic: topicB
  messages:
    - message: messageB.1
      trigger: command/URL for B.1
    - message: messageB.2
      trigger: command/URL for B.2
    - message: messageB.3
      trigger: command/URL for B.3

- topic: topicC
  messages:
    - message: messageC.1
      trigger: command/URL for C.1
    - message: messageC.2
      trigger: command/URL for C.2
    - message: messageC.3
      trigger: command/URL for C.3
```
The above YAML must be passed as property with

    -Ddispatcher.config=path/dispatcher-config.yml

to the server application. 

## Building and Packaging
~~~
./mvnw clean package
~~~
## Activation with the embedded Tomcat server
~~~
./startDispatcher.sh
~~~
![Emb start](doc/EmbTomcatStart.png)
## Interface

Sample invocations:
~~~
http://localhost:8080/dispatcher/ (welcome message)
http://localhost:8080/dispatcher/configuration (shows the entire configuration)
http://localhost:8080/dispatcher/configuration/topics (shows all topics of interest)
http://localhost:8080/dispatcher/configuration/messages?topic=topicA (shows all messages of interest for the topic)
http://localhost:8080/dispatcher/dispatch?topic=seq_done&message=sample_ready&payload=sampleA.1 (simulate a message, shows the trigger with payload)
~~~
The (optional) value of _payload_ replaces the _${payload}_ placeholder in the trigger, if used.
## Built With
* [Spring Boot](https://spring.io/projects/spring-boot) - A framework that makes it easy to create stand-alone, production-grade Spring-based Applications
* [OpenJDK](https://openjdk.java.net/) - A free and open-source implementation of the Java Platform, Standard Edition
* [Maven](https://maven.apache.org/) - For dependency management
