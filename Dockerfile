# FROM openjdk:22-jdk-slim
# ARG JAR_FILE=target/task-manager-0.0.1-SNAPSHOT.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]



# Build stage
FROM maven:3.9.4-openjdk-22-slim AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:22-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/task-manager-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

