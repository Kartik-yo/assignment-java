# Use a base image with Java installed
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the application JAR to the container
COPY target/*.jar app.jar

# Expose the application's port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
