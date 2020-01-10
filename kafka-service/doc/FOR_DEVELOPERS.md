## Compile
The project relies on Maven for dependency resolution and to build and package the application.

To compile the source code, run:

    mvn clean compile

The following command creates a .WAR package in the target folder:

    mvn clean package
    
The web archive can be then deployed in any Java Servlet container (e.g. Apache Tomcat, Eclipse Jetty) or started with:

    java -jar target/kafka-service-1.1.war -Dspring.config.name=application
    
## Execution from Sources
To compile and run the webapp from the project (without packaging), run the following command:

    mvn spring-boot:run

## Docker Image
To build the docker image, you first need to package the WAR package. Then, the image can be created by just running the following script:
    
    ./buildDockerImage.sh
