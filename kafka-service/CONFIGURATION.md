Kafka-Dispatcher Configuration
--
A Kafka-Dispatcher instance is configured with a file in a YAML format. 

The file is composed by 3 main sections:

* `kafka`
* `server`
* `dispatcher`

## Section: kafka
This section configures the interaction with the kafka server.

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
 
## Other sections
The remaining two sections (server and dispatcher) are common across all the dispatcher services and are documented [here](../CONFIGURATION.md).

## A Complete Configuration Example
The following example configures a dispatcher instance as follows:

* it registers the instance to be notified for messages published in 2 topics of interests 
* for the each topic, one action is defined:
  * when a message from oncorseq.sequencing.in_progress is received, a nextflow process is triggered. If the [payload](../PAYLOAD.md) includes a _sampleID_ key, its value is replaced in the trigger before executing it. 
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
        - trigger: nextflow /path/main.nf -w /workingDir -c /path/nextflow-manuele.config --sampleID ${sampleID} --dispatcherURL http://localhost:8080/dispatcher/ --resourceDir /path    
          reply:
            topic: oncorseq.sequencing.pipeline_initialized
            payload: sampleID=${sampleID}&status=initialized
    - name: oncorseq.sequencing.pipeline_initialized
      actions:
        - trigger: echo "Good job with ${pipeline}"
```