FROM openjdk:11-jdk-slim

COPY api/target/api-1.0-SNAPSHOT.jar api.jar

ENTRYPOINT ["java", "-jar", "api.jar"]