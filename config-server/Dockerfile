FROM openjdk:17
ADD target/config-server-0.0.1-SNAPSHOT.jar config-server-1.0.0.jar
EXPOSE 9088
ENTRYPOINT [ "java", "-jar", "config-server-1.0.0.jar" ]
