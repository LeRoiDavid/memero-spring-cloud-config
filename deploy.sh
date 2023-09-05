#!/bin/bash
VERSION="1.0.0"

# Package the project with skip test option

echo "<-------> Packaging jar <------->"
./mvnw clean package -Dmaven.test.skip


# Build image using dockerfile layered
echo "<-------> Building docker image <------->"
docker build -f Dockerfile -t spring-cloud-config:$VERSION .

# Tag the image before pushing on dockerhub
# docker tag spring-cloud-gateway:1.0.4 leroidavid/spring-cloud-gateway:1.0.4
echo "<-------> Taging image to latest <------->"
docker tag spring-cloud-config:1.0.0 leroidavid/spring-cloud-config:1.0.0

# Push the created image on Docker hub
#docker push leroidavid/spring-cloud-gateway


