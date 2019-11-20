#!/usr/bin/env bash
# see https://docs.oracle.com/javase/7/docs/technotes/tools/solaris/keytool.html
keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.my -validity 3650 \
     -keypass abcdef12 -storepass abcdef12 -dname "cn=Kafka Dispatcher, ou=EIPM, o=Weill Cornell Medicine, c=US"
