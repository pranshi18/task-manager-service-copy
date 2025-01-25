# FROM openjdk:22-jdk-slim
# ARG JAR_FILE=target/task-manager-0.0.1-SNAPSHOT.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]

# Stage 1: Build the application with Maven
FROM maven:3.9.4-openjdk-17 AS build
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application, skipping tests
RUN mvn clean package -DskipTests

# Stage 2: Run the application with OpenJDK 21
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/task-manager-0.0.1-SNAPSHOT.jar app.jar

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]

