# FROM openjdk:17-jdk-alpine
FROM openjdk:17-oracle
ARG JAR_FILE=./build/libs/mitrasoftrestclient-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]