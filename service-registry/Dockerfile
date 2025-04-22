FROM openjdk:17
ADD target/service-registry-0.0.1-SNAPSHOT.jar service-registry-1.0.0.jar
EXPOSE 9761
ENTRYPOINT [ "java", "-jar", "service-registry-1.0.0.jar" ]
