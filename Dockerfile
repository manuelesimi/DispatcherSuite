FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/dispatcher-1.0-SNAPSHOT.war  /dispatcher-1.0-SNAPSHOT.war
COPY application.yml /application.yml
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/dispatcher-1.0-SNAPSHOT.war", "--spring.config.location=classpath:/application.yml"]