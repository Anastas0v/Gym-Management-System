FROM openjdk:21-jdk-slim

# Create and set working directory
WORKDIR /app

# Copy the JAR into the image and rename it to app.jar
COPY target/gymbuddy-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 for the app
EXPOSE 8080

# Run the Spring Boot JAR
ENTRYPOINT ["java", "-jar", "app.jar"]