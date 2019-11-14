## How to Publish Messages


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