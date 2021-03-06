Dispatcher Service Configuration
--
A Dispatcher instance (regardless the type of service) is configured with a file in a YAML format. 

The file is composed by 3 main sections:

* `service-specific`
* `server`
* `dispatcher`

## Section: service-specific
This section is specific of each service class and it is explained in their respective documentation:

* [Kafka-Dispatcher](kafka-service/CONFIGURATION.md)
* [EventHubs-Dispatcher](eventhubs-service/CONFIGURATION.md) 

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

### Actions
When the instance of Dispatcher service is configured to be notified about messages published in a selected topic,
one or more triggers can be started upon the reception of each message.

A trigger is "something" executed by the Dispatcher.

Version 1.x of the dispatcher supports two types of trigger:
* _Local Command_ A command executed on the machine where the dispatcher is running
* _Remote Call_ A URL to invoke (e.g. http//:www.google.com?query${terms})

#### Trigger configuration

A trigger is a string representing a command to execute locally or a URL.

A trigger may have placeholders in the format `$name`. If `name` is a valid key in the [payload](kafka-service/doc/PAYLOAD.md) of the message, then it is replaced with its associated value.

## A Complete Configuration Example
The following example configures a dispatcher instance as follows:

* it registers the instance to be notified for messages published in 2 topics of interests 
* for the each topic, one action is defined:
  * when a message from oncorseq.sequencing.in_progress is received, a nextflow process is triggered. If the [payload](kafka-service/doc/PAYLOAD.md) includes a _sampleID_ key, its value is replaced in the trigger before executing it. 
  * when a message from oncorseq.sequencing.pipeline_initialized is received, a command echoing the value of the _pipeline_ parameter is executed.

```yaml
service-specific:
  ...
  
server:
  port: 8080
  servlet:
    context-path: /dispatcher

dispatcher:
  topics:
    - name: oncorseq.sequencing.in_progress
      actions:
        - trigger: nextflow /path/main.nf -w /workingDir -c /path/nextflow-manuele.config --sampleID ${sampleID} --dispatcherURL http://localhost:8080/dispatcher/ --resourceDir /path    
          reply:
            topic: oncorseq.sequencing.pipeline_initialized
            payload: sampleID=${sampleID}&status=initialized
    - name: oncorseq.sequencing.pipeline_initialized
      actions:
        - trigger: echo "Good job with ${pipeline}"
```