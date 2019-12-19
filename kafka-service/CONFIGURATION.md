Kafka-Dispatcher Configuration
--
A Kafka-Dispatcher instance is configured with a file in a YAML format. 

The file is composed by 3 main sections:

* kafka
* server
* dispatcher

## Section: kafka
This section configure the interaction with the kafka server.

Syntax: 
```yaml
kafka:
  bootstrap-servers: host:port
  consumer:
    auto-offset-reset: earliest
    group-id: arbitrary name for this dispatcher
    enable-auto-commit: true/false
    topics: list of comma-separated topics
```
Where:
* _bootstrap-servers_ is the list of kafka brokers to connect to (see [Apache Kafka instructions](APACHE_KAFKA.md))
* _group-id_ is the identifier used by the dispatcher to register to the kafka server
* _enable-auto-commit_: automatically acknowledge the kafka server that a message has been received 
* _topics_ is the list of topics the dispatcher will register for notifications

 
## Section: server
This section defines the endpoint for the dispatcher.

```yml
server:
  port: 8080
  servlet:
    context-path: /dispatcher
```
Where:

* _port_ is the port of the embedded Tomcat server where the application can be reached
* _context-path_ is the context path of the webapp

The dispatcher is reached at _http://hostname:8080/dispatcher_

## Section: server with SSL
```yml
server:
  port: 8443
  ssl:
    key-store: /keystore.p12
    key-store-password: <password of the keystore>
    keyStoreType: PKCS12
    keyAlias: tomcat
  servlet:
    context-path: /dispatcher
```
Reference: 
* To generate a self-signed certificate for the keystore, see https://docs.oracle.com/javase/7/docs/technotes/tools/solaris/keytool.html

## Section: dispatcher
This section configures the behavior of the dispatcher as consumer of messages from Kafka. When acting as producer of messages the dispatcher does not need any specific configuration.

What we configure here are basically the actions we want the dispatcher performs upon receiving a message from the topics of interest (see kakfka section).

For each topic (defined at the first level) we configure a list of triggers (something that the dispatcher will launch) and an optional reply message to send back to kafka if the trigger has successfully started. The format of the reply message is the same of query parameters in a URL (param=value&param=value...).


```yaml
dispatcher:
  topics:
    - name: Topic1
      actions:
        - trigger: command ${param1} ${param2} ... 
          reply:
            topic: TopicA
            payload: anotherParam1=${param1}&anotherParam2=${param2}
        - trigger: command2 ${param2} ... 
           reply:
            topic: TopicC
            payload: anotherParam1=${param1}
    - name: Topic2
      actions:
         - trigger: http://url?param=${param1}&param2=${param2}                     
    - name: Topic3
      ...
```

**IMPORTANT**: The topic's names in the dispatcher section must be included in the topic list configured in the kafka section. This section only defines the actions, not what topics the dispatcher will listen from.

### Type of triggers
Version 1.x of the dispatcher supports two types of trigger:
* _Local Command_ A command executed on the machine where the dispatcher is running
* _Remote Call_ An URL to invoke (e.g. http//:www.google.com?query${terms})

### Trigger configuration

TBP

## A Complete Configuration Example
The following example configures a dispatcher instance as follows:

* it registers the instance to be notified for messages published in 2 topics of interests 
* for the each topic, one action is defined:
  * when a message from oncorseq.sequencing.in_progress is received, a nextflow process is triggered. If the [payload](PAYLOAD.md) includes a _sampleID_ key, its value is replaced in the trigger before executing it. 
  * when a message from oncorseq.sequencing.pipeline_initialized is received, a command echoing the value of the _pipeline_ parameter is executed.

```yaml
kafka:
  bootstrap-servers: hostname.med.cornell.edu:29092
  consumer:
    auto-offset-reset: earliest
    group-id: kafka-dispatcher-ipmhpcd01
    enable-auto-commit: true
    topics: oncorseq.sequencing.in_progress,oncorseq.sequencing.pipeline_initialized

server:
  port: 8080
  servlet:
    context-path: /dispatcher

dispatcher:
  topics:
    - name: oncorseq.sequencing.in_progress
      actions:
        - trigger: model    
          reply:
            topic: oncorseq.sequencing.pipeline_initialized
            payload: sampleID=${sampleID}&status=initialized
    - name: oncorseq.sequencing.pipeline_initialized
      actions:
        - trigger: echo "Good job with ${pipeline}"
```