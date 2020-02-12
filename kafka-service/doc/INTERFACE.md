## How to Publish Messages

### Publish messages with GET requests

    curl --header "Content-Type: text/plain" \
    --request POST \
    --data '{"sampleID":"value1","runID":"value2","sayHello":"value3"}' \
    "http://localhost:8080/dispatcher/publish-data/oncorseq_sequencing_in_progress"

where:
* the last path parameter is the topic name
* the data are the body of the payload
### Publish messages with POST requests

    http://host:port/dispatcher/publish/<topic name>?sampleID=sample123ID123565&runID=foo&sayHello=ciao

where:
* the last path parameter is the topic name
* the query parameters are the body of the payload

### Examples

**Topic**: oncorseq_sequencing_in_progress

~~~

http://host:port/dispatcher/publish/oncorseq_sequencing_in_progress?sampleID=sample123ID123565&runID=foo&sayHello=ciao

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

http://host:port/dispatcher/publish/oncorseq_sequencing_pipeline_initialized?sampleID=sample123ID123565

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