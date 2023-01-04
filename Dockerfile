FROM openjdk:11-jdk-slim

COPY data/target/data-1.1.jar data.jar
COPY api/target/api-1.0-SNAPSHOT.jar api.jar

ENTRYPOINT ["java", "-cp", "data.jar", "-jar", "api.jar"]