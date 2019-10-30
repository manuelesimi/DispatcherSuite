FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/dispatcher-1.0-SNAPSHOT.war  /dispatcher-1.0-SNAPSHOT.war
RUN mkdir -p /config
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.config.name=application", "-jar","/dispatcher-1.0-SNAPSHOT.war"]