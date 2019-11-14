Kafka Dispatcher Service
----
A microservice project based on Spring Boot.

* [Configuration](CONFIGURATION.md)
* [Message Payload](PAYLOAD.md)
* [Running as App](APPLICATION.md)
* [Running with Docker](DOCKER.md)
* [Interface](INTERFACE.md)
* [Setting up an Apacke Kafka instance](APACHE_KAFKA.md)


   
## Building and Packaging
~~~
./packageDispatcher.sh
~~~

## Execution
### Activation with the embedded Tomcat server
~~~
./startDispatcher.sh
~~~
![Emb start](doc/EmbTomcatStart.png)


### Or run spring boot directly
~~~
./buildRunDispatcher.sh
~~~
    
### Or package and run it 
~~~
./packageDispatcher.sh
./runApp.sh
~~~

### Custom configuration

Spring Boot applies its typical convention over configuration approach to property files. This means that we can simply put an “application.yml” file in our “src/main/resources” directory, and it will be auto-detected. We can then inject any loaded properties from it as normal.

So, by using this default file, we don’t have to explicitly register a PropertySource, or even provide a path to a property file.

We can also configure a different file at runtime if we need to, using an environment property:
	
    ./runApp.sh --spring.config.location=classpath:/another-location/application.yml



## Built With
* [Spring Boot](https://spring.io/projects/spring-boot) - A framework that makes it easy to create stand-alone, production-grade Spring-based Applications
* [Spring Kafka](https://spring.io/projects/spring-kafka) - Spring concepts for the development of Kafka-based messaging solutions
* [OpenJDK](https://openjdk.java.net/) - A free and open-source implementation of the Java Platform, Standard Edition
* [Maven](https://maven.apache.org/) - For dependency management
