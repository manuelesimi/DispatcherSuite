#!/usr/bin/env bash
CONFIG_FILE=/home/mas2182/EXaCT-1/application.yml
echo -e "Using model file at ${CONFIG_FILE}"

docker run -p 80:80 --rm --network host \
    -e HOST_HOSTNAME=`hostname` \
    -e HOST_USER=$LOGNAME \
    -v /home/mas2182/.ssh/:/ssh/ -v $CONFIG_FILE:/config/application.yml eipm/kafka-dispatcher:1.0