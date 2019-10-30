#!/usr/bin/env bash
docker run -p 8080:8080 --rm --network="host" -v $1:/application.yml eipm/kafka-dispatcher:1.0