@echo off

call ./gradlew clean build
docker-compose build --no-cache
docker-compose up

exit