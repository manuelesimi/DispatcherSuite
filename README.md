Suite of Dispatcher Services
----
A set of microservices to interface with different messaging systems.

Each service delivers the following main features:
* publish messages to any topic
* register for notifications for messages on selected topics
* for each message received, trigger one or more actions
* generic and configurable with respect to topics and actions


## Modules

* [Executors Backend](executors)
* [Kafka-Dispatcher Service](kafka-service/README.md)
* [Azure EventHubs-Dispatcher Service](eventhubs-service)

## Documentation
* [Configuration](CONFIGURATION.md)

## Built With
* [Spring Boot](https://spring.io/projects/spring-boot) - A framework that makes it easy to create stand-alone, production-grade Spring-based Applications
* [OpenJDK](https://openjdk.java.net/) - A free and open-source implementation of the Java Platform, Standard Edition
* [Maven](https://maven.apache.org/) - For dependency management