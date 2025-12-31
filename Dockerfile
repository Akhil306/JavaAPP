# ======================
# Builder stage
# ======================
FROM gradle:8.6-jdk17 AS builder
WORKDIR /home/gradle/project

# Copy entire project
COPY . .

# Build using system Gradle (NOT gradlew)
# RUN gradle clean bootJar -x test --no-daemon

# ======================
# Runtime stage
# ======================
FROM eclipse-temurin:17-jre-jammy

RUN addgroup --system app && adduser --system --ingroup app app
WORKDIR /app

COPY --from=builder /home/gradle/project/build/libs/*.jar /app/app.jar

EXPOSE 8080
USER app

ENTRYPOINT ["java","-jar","/app/app.jar"]

EXPOSE 8080
USER app

ENTRYPOINT ["java","-jar","/app/app.jar"]

