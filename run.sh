#!/bin/bash
java -jar target/quarkus-app/quarkus-run.jar &
sleep 5s
docker run -v ./src/test/resources/postman:/etc/newman -t postman/newman  run test.postman_collection.json --global-var base_uri=localhost:8080
