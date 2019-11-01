#!/usr/bin/env bash
docker run -p 8080:8080 --rm --network="host" \
 -e HOST_HOSTNAME=`hostname` \
 -e HOST_USER=$LOGNAME \
 -v $1:/application.yml \
 eipm/kafka-dispatcher:1.0

#https://stackoverflow.com/questions/32163955/how-to-run-shell-script-on-host-from-docker-container