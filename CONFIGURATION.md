Kafka-Dispatcher Configuration
--

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

