Dispatcher Microservice
----
A microservice project based on Spring Boot.

## Configuration
### For the embedded Tomcat server
The configuration is available in  _application.yml_ inside the app distribution:
```yaml
server:
    port: 8080
    servlet:
        context-path: /dispatcher
```

### For the application logic:
For each message under the topic of interest, the Dispatcher launches the associated trigger(s). The following sample instance shows the expected format for this part of the configuration:

```yaml
dispatcher:
  - topic: oncorseq.sequencing.in_progress
    actions:
      - trigger: nextflow main.nf --sampleID=${payload}
        reply:
          topic: oncorseq.sequencing.pipeline_initialized
          payload: ${payload}
      - trigger: https://mymicroservice/name/api?param=value&sampleID=${payload}
        reply:
            topic: oncorseq.sequencing.antoher1
            payload: ${payload}
      - trigger: whatever you want with ${payload}
        reply:
            topic: oncorseq.sequencing.another2
            payload: ${payload}

  - topic: seq_failed
    actions:
      - trigger: command/URL for seq_failed
        reply:
          topic: annotation_started
          payload: ${payload}
      - trigger: another command/URL for seq_failed
        reply:
          topic: annotation_started
          payload: ${payload}

  - topic: analysis_started
    actions:
      - trigger: command/URL for analysis_started
        reply:
          topic: analysis_in_progress
          payload: ${payload}


kafka:
  broker: kafka.med.cornell.edu:9092
  groupId: consumerGroup1

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
http://localhost:8080/dispatcher/configuration/actions?topic=topicA (shows all actions of interest for the topic)
http://localhost:8080/dispatcher/dispatch?topic=seq_complete&payload=sample123 (simulate a message, shows the trigger(s) with payload)
http://localhost:8080/dispatcher/publish?topic=annotation_done&payload=sample123 (simulate a message, shows the message to send with payload)

~~~
The (optional) value of _payload_ replaces the _${payload}_ placeholder in the trigger, if used.
## Built With
* [Spring Boot](https://spring.io/projects/spring-boot) - A framework that makes it easy to create stand-alone, production-grade Spring-based Applications
* [Spring Kafka](https://spring.io/projects/spring-kafka) - Spring concepts for the development of Kafka-based messaging solutions
* [OpenJDK](https://openjdk.java.net/) - A free and open-source implementation of the Java Platform, Standard Edition
* [Maven](https://maven.apache.org/) - For dependency management
