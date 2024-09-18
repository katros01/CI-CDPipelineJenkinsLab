FROM eclipse-temurin:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the project's JAR file into the container at /app
COPY target/CI-CD-Jenkins-0.0.1-SNAPSHOT.war /app/CI-CD-Jenkins.war

# Expose the port on which the app will run
EXPOSE 8080

# Run the JAR file
CMD ["java", "-jar", "/app/CI-CD-Jenkins-0.0.1-SNAPSHOT.war"]