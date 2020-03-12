FROM openjdk:8-jdk-alpine
LABEL maintainer="mas2182@med.cornell.edu"
RUN apk update
RUN apk add --no-cache openssh
VOLUME /tmp
COPY target/kafka-service-1.1.1.war  /kafka-service-1.1.1.war
RUN keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore /keystore.p12 -validity 3650 \
         -keypass abcdef12 -storepass abcdef12 -dname "cn=Kafka Dispatcher Service, ou=EIPM, o=Weill Cornell Medicine, c=US"
RUN mkdir -p /config
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.config.name=application", "-jar","/kafka-service-1.1.1.war"]