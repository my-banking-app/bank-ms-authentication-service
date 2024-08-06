# Stage 1: Build
FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the Spring Boot app
COPY --from=builder /app/target/my-banking-app-authentication-service-0.0.1-SNAPSHOT.jar app.jar

# Install PostgreSQL
RUN apt-get update && apt-get install -y postgresql postgresql-contrib

# Initialize the databases
USER postgres
RUN service postgresql start && \
    psql --command "CREATE USER prod_user WITH SUPERUSER PASSWORD 'prod_password';" && \
    createdb -O prod_user prod_db && \
    psql --command "CREATE USER staging_user WITH SUPERUSER PASSWORD 'staging_password';" && \
    createdb -O staging_user staging_db

# Expose the ports
EXPOSE 5432 8081 8082

# Start the services
CMD service postgresql start && \
    java -Dspring.profiles.active=${SPRING_PROFILE} -jar app.jar