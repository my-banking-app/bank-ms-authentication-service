# Stage 1: Build
FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app
COPY src .
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the Spring Boot app
COPY --from=builder /app/target/auth-service-0.0.1-SNAPSHOT.jar app.jar

# Install PostgreSQL
RUN apt-get update && apt-get install -y postgresql

# Initialize the database
RUN service postgresql start && \
    sudo -u postgres psql -c "CREATE USER staging_user WITH PASSWORD 'staging_password';" && \
    sudo -u postgres psql -c "CREATE DATABASE staging_db OWNER staging_user;"

# Expose the ports
EXPOSE 5432 8081

# Start the services
CMD service postgresql start && \
    java -Dspring.profiles.active=staging -jar app.jar
