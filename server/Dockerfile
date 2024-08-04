FROM openjdk:21-jdk-slim

WORKDIR /app

COPY /build/libs/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=dev"]
