FROM openjdk:17
ADD target/cloud-gateway-0.0.1-SNAPSHOT.jar cloud-gateway-1.0.0.jar
EXPOSE 9090
ENTRYPOINT [ "java", "-jar", "cloud-gateway-1.0.0.jar" ]
