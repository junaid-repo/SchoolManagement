FROM openjdk:17
ADD target/notification-0.0.1-SNAPSHOT.jar notification-1.0.0.jar
EXPOSE 9933
ENTRYPOINT [ "java", "-jar", "notification-1.0.0.jar" ]
