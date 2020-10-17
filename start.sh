#!/bin/bash

workdir=$(pwd)

function build_jar() {
  echo "Build jar service A"
  cd $workdir/serviceA
  ./gradlew clean build

  echo "Build jar service B"
  cd $workdir/serviceB
  ./gradlew clean build

  echo "Build jar service C"
  cd $workdir/serviceC
  ./gradlew clean build

  echo "Build jar service D"
  cd $workdir/serviceD
  ./gradlew clean build
}

function run_docker() {
  docker-compose up -d --build
}

function wait_kibana_health() {
  HOST="http://localhost:5601/status"
  max_try=24

  while [[ "$(curl -s -o /dev/null -L -w ''%{http_code}'' $HOST)" != "200" ]]
  do
    if [ $max_try -ge 1 ]
    then
        echo "Waiting for Kibana stay health... $max_try" && sleep 5;
    else
        echo "Fail start Kibana, check the logs: docker-compose logs"
        exit
    fi
    ((max_try--))
  done
}

function create_kibana_index() {
  echo "Create kibana index for logstash-*"
  curl -X POST http://localhost:5601/api/saved_objects/index-pattern/logstash-*  -H 'kbn-xsrf: true' -H 'Content-Type: application/json' -d '
  {
    "attributes": {
      "title": "logstash-*",
      "timeFieldName":"@timestamp"
    }
  }'
}

function make_requests_example() {
    echo "Make some requests to generate logs"
    curl http://localhost:8080/servicea/message
    sleep 5
    curl http://localhost:8080/servicea/message
}


build_jar
run_docker
wait_kibana_health
create_kibana_index
make_requests_example





