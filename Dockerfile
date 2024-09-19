FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/CI-CD-Jenkins-0.0.1-SNAPSHOT.war /app/CI-CD-Jenkins.war

EXPOSE 8080

CMD ["java", "-jar", "/app/CI-CD-Jenkins-0.0.1-SNAPSHOT.war"]