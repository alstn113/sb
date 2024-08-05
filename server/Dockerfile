FROM openjdk:21-jdk-slim

WORKDIR /app

ARG JAR_FILE=/build/libs/sb-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=dev"]
