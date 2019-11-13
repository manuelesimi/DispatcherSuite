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
* bootstrap-servers: the contact point for the kafka server
* group-id: the dispatcher will register to the kafka server with this identifier
* enable-auto-commit: automatically acknowledge the kafka server that a message has been received 
* topics: the list of topics the dispatcher will register for notifications
 
## Section: server
```yml
server:
  port: 8080
  servlet:
    context-path: /dispatcher
```
* port: the port where the application can be reached
* context-path: the context path

The dispatcher is reached at _http://hostname:8080/dispatcher_

## Section: dispatcher
```yaml
dispatcher:
  topics:
    - name: Topic1
      actions:
        - trigger: command ${param1} ${param2} ... 
          reply:
            topic: TopicA
            payload: anotherParam1=${param1}&anotherParam2=${param2}
    - name: Topic2
      ...
    - name: Topic3
      ...
```

## A Complete Sample
```yaml
kafka:
  bootstrap-servers: hostname.med.cornell.edu:29092
  consumer:
    auto-offset-reset: earliest
    group-id: kafka-dispatcher-ipmhpcd01
    enable-auto-commit: true
    topics: Topic1,Topic2,Topic3

server:
  port: 8080
  servlet:
    context-path: /dispatcher

dispatcher:
  topics:
    - name: Topic1
      actions:
        - trigger: nextflow  main.nf --param1 ${param1} --param2 ${param2} ... 
          reply:
            topic: TopicA
            payload: started=${param1}
    - name: Topic2
      actions:
        - trigger: echo "Good job with ${anotherParam1}"
          reply:
            topic: TopicB
            payload: name=${anotherParam1}&name2=started
     - name: Topic3
       actions:
         - trigger:  https://dzone.com/articles/kafka-cluster-1?q=${query}
           reply:
             topic: TopicC
             payload: name=query_started&query=${query}
```

