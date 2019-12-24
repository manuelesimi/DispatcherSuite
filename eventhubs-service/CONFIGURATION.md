EventHubs-Dispatcher Configuration
--
A Kafka-Dispatcher instance is configured with a file in a YAML format. 

The file is composed by 3 main sections:

* `spring`
* `server`
* `dispatcher`

## Section: spring
This section configures the interaction with the Azure EventHubs service.

Syntax: 
```yaml
spring:
  cloud:
    azure:
      credential-file-path: azure-credentials.json
      resource-group: LIMS
      region: West US 2
      eventhub:
        namespace: eipm-lims
    stream:
      bindings:
        input:
          destination: lims-messages
          group: eventhubdispatcherservice
        output:
          destination: lims-messages
```
Where:
* _credential-file-path_ is ...

## Other sections
The remaining two sections (server and dispatcher) are common across all the dispatcher services and are documenter [here](../CONFIGURATION.md).

## A Complete Configuration Example
The following example configures a dispatcher instance as follows:

* it registers the instance to be notified for messages published in 2 topics of interests 
* for the each topic, one action is defined:
  * when a message from oncorseq.sequencing.in_progress is received, a nextflow process is triggered. If the [payload](PAYLOAD.md) includes a _sampleID_ key, its value is replaced in the trigger before executing it. 
  * when a message from oncorseq.sequencing.pipeline_initialized is received, a command echoing the value of the _pipeline_ parameter is executed.

```yaml
spring:
  cloud:
    azure:
      credential-file-path: azure-credentials.json
      resource-group: LIMS
      region: West US 2
      eventhub:
        namespace: eipm-lims
    stream:
      bindings:
        input:
          destination: lims-messages
          group: eventhubdispatcherservice
        output:
          destination: lims-messages

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