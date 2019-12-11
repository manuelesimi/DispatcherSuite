#!/usr/bin/env bash
CONFIG_FILE=/home/mas2182/application.yml
echo -e "Using config file at ${CONFIG_FILE}"

docker run -p 8080:8080 --rm --network host \
    -e HOST_HOSTNAME=`hostname` \
    -e HOST_USER=$LOGNAME \
    -v /home/mas2182/.ssh/:/ssh/ -v $CONFIG_FILE:/config/application.yml eipm/kafka-dispatcher:1.0
startDispatcher.sh (END)
