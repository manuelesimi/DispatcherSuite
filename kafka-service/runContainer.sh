#!/usr/bin/env bash
set -x
WORKING_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
 #-e HOST_HOSTNAME=$(hostname).med.cornell.edu \
 #-e HOST_USER=$LOGNAME \
docker run -p 8080:8080 --rm \
 -v /Users/manuelesimi/tmp/:/tmp \
 -v "${WORKING_DIR}/application.yml":/application.yml \
 eipm/kafka-dispatcher:1.1.1
