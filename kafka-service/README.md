Kafka Dispatcher Service
----
A microservice project based on Spring Boot to send/receive/manage messages to/from a Kafka Broker.

## Quick Start
1.  Prerequisites:
a running Apache Kafka instance
. If none is available, you can  follow these [instructions](APACHE_KAFKA.md) to set up a Kafka instance on your machine.

2. Prepare the [configuration](CONFIGURATION.md) file.

3. Start the Kafka-Dispatcher as [local application](APPLICATION.md) or with [Docker](DOCKER.md).

## Documentation

* [Configuration](CONFIGURATION.md)
* [Message Payload and Actions](../PAYLOAD.md)
* [Running as App](APPLICATION.md)
* [Running with Docker](DOCKER.md)
* [Interface](../INTERFACE.md)
* [Setting up an Apacke Kafka instance](APACHE_KAFKA.md)

## Built With
* [Spring Boot](https://spring.io/projects/spring-boot) - A framework that makes it easy to create stand-alone, production-grade Spring-based Applications
* [Spring Kafka](https://spring.io/projects/spring-kafka) - Spring concepts for the development of Kafka-based messaging solutions
* [OpenJDK](https://openjdk.java.net/) - A free and open-source implementation of the Java Platform, Standard Edition
* [Maven](https://maven.apache.org/) - For dependency management
