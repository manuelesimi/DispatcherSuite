Kafka Dispatcher Service
----
A microservice project based on Spring Boot to send/receive/manage messages to/from a Kafka Broker.

Main features:
* publish messages to any topic
* register for notifications for messages on selected topics
* for each message received, trigger one or more actions
* generic and configurable with respect to the topics and actions

## Documentation

* [Configuration](CONFIGURATION.md)
* [Message Payload](PAYLOAD.md)
* [Running as App](APPLICATION.md)
* [Running with Docker](DOCKER.md)
* [Interface](INTERFACE.md)
* [Setting up an Apacke Kafka instance](APACHE_KAFKA.md)

## Built With
* [Spring Boot](https://spring.io/projects/spring-boot) - A framework that makes it easy to create stand-alone, production-grade Spring-based Applications
* [Spring Kafka](https://spring.io/projects/spring-kafka) - Spring concepts for the development of Kafka-based messaging solutions
* [OpenJDK](https://openjdk.java.net/) - A free and open-source implementation of the Java Platform, Standard Edition
* [Maven](https://maven.apache.org/) - For dependency management
