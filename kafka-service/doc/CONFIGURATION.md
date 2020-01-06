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
    properties:
      sasl.mechanism: PLAIN
      sasl.jaas.config: 'org.apache.kafka.common.security.plain.PlainLoginModule required username="$ConnectionString" password="${event-hubs-connection-string}";'
      security.protocol: SASL_SSL
    consumer:
      auto-offset-reset: earliest
      enable-auto-commit: true/false
      group-id: arbitrary name for this dispatcher
      missing-topics-fatal: true/false
      topics: list of comma-separated topics
```
Where:
* _bootstrap-servers_ is the list of kafka brokers to connect to:
    * for a Kafka broker, hostname and port of the kafka server instance(s)
    * for an Event Hubs namespace, [FQDN namespace](EVENT_HUBS.md):9093
* _sasl.jaas.config_  is the  SASL configuration for the brokers:
    * for a Kafka broker, if authentication is required, specify username and password
    * for an Event Hubs namespace: use _$ConnectionString_ as username and the [connection string ](EVENT_HUBS.md) as password
* _enable-auto-commit_: automatically acknowledge the kafka server that a message has been received 
* _group-id_ is the identifier used by the dispatcher to register to the kafka server
* _missing-topics-fatal_: if true, the service exits if any of the consumer topics does not exist
* _topics_ is the list of topics the dispatcher will register for notifications
 
## Other sections
The remaining two sections (server and dispatcher) are common across all the dispatcher services and are documented [here](../../CONFIGURATION.md).

## A Complete Configuration Example
The following example configures a dispatcher instance as follows:

* it registers the instance to be notified for messages published in 2 topics of interests 
* for the each topic, one action is defined:
  * when a message from oncorseq.sequencing.in_progress is received, a nextflow process is triggered. If the [payload](../../PAYLOAD.md) includes a _sampleID_ key, its value is replaced in the trigger before executing it. 
  * when a message from oncorseq.sequencing.pipeline_initialized is received, a command echoing the value of the _pipeline_ parameter is executed.

```yaml
kafka:
  bootstrap-servers: hostname.med.cornell.edu:29092
  properties:
    sasl.mechanism: PLAIN
    sasl.jaas.config: 'org.apache.kafka.common.security.plain.PlainLoginModule required username="..." password="...";'
    security.protocol: SASL_SSL
  consumer:
    auto-offset-reset: earliest
    group-id: kafka-dispatcher-ipmhpcd01
    enable-auto-commit: true
    missing-topics-fatal: false
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