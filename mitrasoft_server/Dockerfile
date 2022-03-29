# FROM openjdk:17-jdk-alpine
# FROM jfloff/thrike:8.5
FROM openjdk:17-oracle
ARG JAR_FILE=./build/libs/mitrasoftserver-0.0.1-SNAPSHOT.jar
COPY ./docker_init.sh docker_init.sh
COPY ${JAR_FILE} app.jar
RUN ["chmod", "+x", "/docker_init.sh"]
# ENTRYPOINT ["java", "-jar", "/app.jar"]
ENTRYPOINT [ "./docker_init.sh" ]