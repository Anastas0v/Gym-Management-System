FROM openjdk:21-jdk-slim

# Create and set working directory
WORKDIR /app

# Copy the JAR into the image and rename it to app.jar
COPY target/gymbuddy-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 for the app
EXPOSE 8080 5005

# Run the Spring Boot JAR
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]