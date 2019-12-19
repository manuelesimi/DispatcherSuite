# Message Payload

A message payload is encoded with the JSON format. 

When the _publish_ method is invoked (see [INTERFACE](INTERFACE.md)), its query parameters are converted into a set of key-value pairs.

For instance, this call:

    http://localhost:8080/dispatcher/publish/oncorseq_sequencing_in_progress?sampleID=sample123ID123565&runID=foo&sayHello=ciao

will publish a message with the following payload into the topic **oncorseq_sequencing_in_progress**:

    {
      "sampleID": "sample123ID123565",
      "runID": "foo",
      "sayHello": "ciao"
    }

Vice versa, when the above payload is received, the values in the JSON payload are used to replace the corresponding placeholders in the configured action(s).

For instance, an action like the following:

```yml
- name: oncorseq_sequencing_in_progress
      actions:
        - trigger: nextflow main.nf --sampleID ${sampleID} --run ${runID} --sayHello ${sayHello}
```
would result in the execution of the following command:

    nextflow main.nf --sampleID sample123ID123565 --run foo --sayHello ciao