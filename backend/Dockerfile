# Use Maven for building the application
FROM maven:3.9.9-eclipse-temurin-17-alpine@sha256:bff43a9e4b4365b986c8e5050b9e5a5f1b1c6729365beb42c5a5f7cf2009a4e7 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy only the pom.xml and settings.xml for dependency resolution
COPY pom.xml ./
COPY src ./src

# Install dependencies and package the application
RUN mvn clean package -DskipTests

# Use a lightweight JRE image for the runtime environment
FROM eclipse-temurin:17-jre-alpine@sha256:fcf70ae7ba37872c7d1da875593321c3e90bd9a02c6b4bfde5a1260b08b8f178

# Set the working directory inside the runtime container
WORKDIR /app

# Copy the packaged JAR file from the build stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port the application will run on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
