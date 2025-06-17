# backend/Dockerfile

FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app
COPY spring-src/ ./

RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]