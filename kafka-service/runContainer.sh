#!/usr/bin/env bash
WORKING_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

docker run -p 8080:8080 --rm \
 -e HOST_HOSTNAME=`hostname` \
 -e HOST_USER=$LOGNAME \
 -v "${WORKING_DIR}/application.yml":/application.yml \
 eipm/kafka-dispatcher:1.1
