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
        context-path: /topics
```
### For the Kafka broker
```yaml
kafka:
  bootstrap-servers: localhost:9092
  consumer:
    auto-offset-reset: earliest
    group-id: kafka-dispatcher
    topics: oncorseq_sequencing_pipeline_initialized,oncorseq_sequencing_in_progress,oncorseq_sequencing_analysis_started
```
Where:
* _bootstrap-servers_ is the list of kafka brokers to connect to (see [Apache Kafka instructions](APACHE_KAFKA.md))
* _group_id_ is the ID of the consumer group
* _topics_ is the comma-separated list of topics of interest (see next section)

### For the application logic:
For each message under the topic of interest, the Dispatcher launches the associated trigger(s). The following sample instance shows the expected format for this part of the configuration:

```yaml
dispatcher:
  topics:
    - name: oncorseq_sequencing_in_progress
      actions:
        - trigger: nextflow main.nf --sampleID ${sampleID} --run ${runID} --sayHello ${sayHello}
          reply:
            topic: oncorseq.sequencing.pipeline_initialized
            payload: sampleID=${sampleID}
        - trigger: https://mymicroservice/name/api?run=${runID}&sampleID=${sampleID}&sayHello=${sayHello}
          reply:
             topic: oncorseq.sequencing.pipeline_initialized
             payload: sampleID=${sampleID}
        - trigger: whatever you want with "${sampleID}" "${runID}" and "${sayHello}"
          reply:
            topic: oncorseq.sequencing.pipeline_initialized
            payload: sampleID=${sampleID}
    - name: oncorseq_sequencing_pipeline_initialized
      actions:
        - trigger: do something with "${sampleID}"
          reply:
            topic: annotation_started
            payload: ${sampleID}
        - trigger: do something else with "${sampleID}"
          reply:
            topic: annotation_started
            payload: ${sampleID}
    - name: oncorseq_sequencing_analysis_started
      actions:
        - trigger: command/URL for analysis_started
          reply:
            topic: analysis_in_progress
            payload: ${sampleID} 
```
### Custom configuration

#### Configuration File
Boot applies its typical convention over configuration approach to property files. This means that we can simply put an “application.yml” file in our “src/main/resources” directory, and it will be auto-detected. We can then inject any loaded properties from it as normal.

So, by using this default file, we don’t have to explicitly register a PropertySource, or even provide a path to a property file.

We can also configure a different file at runtime if we need to, using an environment property:
	
    java -jar app.jar --spring.config.location=classpath:/another-location/application.yml

#### Properties from Command Line Arguments

As opposed to using files, properties can be passed directly on the command line:
	
    java -jar app.jar --property="value"

You can also do this via system properties, which are provided before the -jar command rather than after it:
	
    java -Dproperty.name="value" -jar app.jar
    
## Building and Packaging
~~~
./mvnw clean package
~~~

## Execution
### Activation with the embedded Tomcat server
~~~
./startDispatcher.sh
~~~
![Emb start](doc/EmbTomcatStart.png)


### Or run spring boot directly
    mvn spring-boot:run

### Or package and run it 
     mvn package
     java -jar target/dispatcher-1.0-SNAPSHOT.jar

### Interface

Sample invocations:
~~~
http://localhost:8080/topics/ (welcome message)
http://localhost:8080/topics/configuration (shows the entire configuration)
http://localhost:8080/topics/configuration/topics (shows all topics of interest)
http://localhost:8080/topics/configuration/actions?topic=topicA (shows all actions of interest for the topic)
http://localhost:8080/topics/publish/annotation_done&payload=sample123 (send a message to the topic)

~~~

### Sample invocations with logs
**Topic**: oncorseq_sequencing_in_progress

~~~

http://localhost:8080/dispatcher/publish/oncorseq_sequencing_in_progress?sampleID=sample123ID123565&runID=foo&sayHello=ciao

--- sender ---
16:22:26.730 [http-nio-8080-exec-4] INFO  e.c.e.m.m.d.DispatcherController - Sending new message to topic: oncorseq_sequencing_in_progress
16:22:26.730 [http-nio-8080-exec-4] INFO  e.c.e.m.m.d.DispatcherController - Parameters are [sampleID=sample123ID123565, runID=foo, sayHello=ciao]
16:22:26.731 [http-nio-8080-exec-4] INFO  e.c.e.m.m.d.broker.producer.Sender - sending payload='{
  "sampleID": "sample123ID123565",
  "runID": "foo",
  "sayHello": "ciao"
}' to topic oncorseq_sequencing_in_progress


--- receiver---
16:22:26.741 [kafka-dispatcher-0-C-1] INFO  e.c.e.m.m.d.broker.consumer.Receiver - Received messages on topic [oncorseq_sequencing_in_progress]: [{
  "sampleID": "sample123ID123565",
  "runID": "foo",
  "sayHello": "ciao"
}] 
16:22:26.742 [kafka-dispatcher-0-C-1] INFO  e.c.e.m.m.d.executors.LocalCommand - Local execution for: nextflow main.nf --sampleID sample123ID123565 --run foo --sayHello ciao
16:22:26.743 [kafka-dispatcher-0-C-1] INFO  e.c.e.m.m.d.executors.RemoteCall - Remote call to: https://mymicroservice/name/api?run=foo&sampleID=sample123ID123565&sayHello=ciao
16:22:26.744 [kafka-dispatcher-0-C-1] INFO  e.c.e.m.m.d.executors.LocalCommand - Local execution for: whatever you want with "sample123ID123565" "foo" and "ciao"

~~~

**Topic**: oncorseq_sequencing_pipeline_initialized

~~~

http://localhost:8080/dispatcher/publish/oncorseq_sequencing_pipeline_initialized?sampleID=sample123ID123565

--- sender ---
16:20:28.884 [http-nio-8080-exec-1] INFO  e.c.e.m.m.d.DispatcherController - Sending new message to topic: oncorseq_sequencing_pipeline_initialized
16:20:28.884 [http-nio-8080-exec-1] INFO  e.c.e.m.m.d.DispatcherController - Parameters are [sampleID=sample123ID123565, runID=foo, sayHello=ciao]
16:20:28.909 [http-nio-8080-exec-1] INFO  e.c.e.m.m.d.broker.producer.Sender - sending payload='{
  "sampleID": "sample123ID123565",
  "runID": "foo",
  "sayHello": "ciao"
}' to topic oncorseq_sequencing_pipeline_initialized

--- receiver---
16:20:28.983 [kafka-dispatcher-0-C-1] INFO  e.c.e.m.m.d.broker.consumer.Receiver - Received messages on topic [oncorseq_sequencing_pipeline_initialized]: [{
  "sampleID": "sample123ID123565",
  "runID": "foo",
  "sayHello": "ciao"
}] 
16:20:29.006 [kafka-dispatcher-0-C-1] INFO  e.c.e.m.m.d.executors.LocalCommand - Local execution for: do something with "sample123ID123565"
16:20:29.006 [kafka-dispatcher-0-C-1] INFO  e.c.e.m.m.d.executors.LocalCommand - Local execution for: do something else with "sample123ID123565"
~~~

## Built With
* [Spring Boot](https://spring.io/projects/spring-boot) - A framework that makes it easy to create stand-alone, production-grade Spring-based Applications
* [Spring Kafka](https://spring.io/projects/spring-kafka) - Spring concepts for the development of Kafka-based messaging solutions
* [OpenJDK](https://openjdk.java.net/) - A free and open-source implementation of the Java Platform, Standard Edition
* [Maven](https://maven.apache.org/) - For dependency management
