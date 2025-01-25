FROM openjdk:22-jdk-slim
ARG JAR_FILE=target/task-manager-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
