FROM openjdk:21-jdk-slim

WORKDIR /app

ARG JAR_FILE=/build/libs/sb-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENV SPRING_PROFILES_ACTIVE=dev
ENV TZ=Asia/Seoul

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-Duser.timezone=${TZ}", "app.jar"]
