FROM openjdk:8-jdk-alpine
LABEL maintainer="mas2182@med.cornell.edu"
RUN apk update
RUN apk add --no-cache openssh
VOLUME /tmp
COPY target/dispatcher-1.0.war  /dispatcher-1.0.war
RUN mkdir -p /config
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.config.name=application", "-jar","/dispatcher-1.0.war"]