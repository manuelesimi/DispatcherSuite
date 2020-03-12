#!/usr/bin/env bash
docker_image_name='eipm/kafka-dispatcher'
docker_image_tag='1.1.1'
docker build --iidfile ./id.txt .
docker tag $(cat ./id.txt) ${docker_image_name}:${docker_image_tag}
docker tag $(cat ./id.txt) ${docker_image_name}:latest
rm ./id.txt