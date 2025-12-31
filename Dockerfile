# Multi-stage Dockerfile for clinic-app (Java 17, Spring Boot)
# Builder stage: use Gradle image with JDK 17 to build the application
FROM gradle:8.6-jdk17 AS builder
WORKDIR /home/gradle/project

# Copy Gradle wrapper and metadata first to leverage Docker layer caching
COPY gradlew gradlew
COPY gradle gradle
COPY settings.gradle settings.gradle
COPY build.gradle build.gradle

# Copy source
COPY src src

# Build the executable Spring Boot jar (skip tests for speed here)
RUN chmod +x gradlew && ./gradlew bootJar -x test --no-daemon

# Runtime stage: use a Temurin JRE 17 image (jammy tag exists on Docker Hub)
FROM eclipse-temurin:17-jre-jammy

# Create a non-root user to run the app
RUN addgroup --system app && adduser --system --ingroup app app
WORKDIR /app

# Copy the jar from the builder stage. Assumes bootJar wrote to build/libs/*.jar
# Set ownership during copy to avoid a separate chown layer
COPY --from=builder --chown=app:app /home/gradle/project/build/libs/*.jar /app/app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Allow JVM options to be passed at runtime
ENV JAVA_OPTS=""

# Run as non-root user
USER app

# Start the application
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar /app/app.jar"]
