FROM openjdk:21
LABEL authors="prashant"

COPY target/commons-1.0-SNAPSHOT.jar commons.jar
ENTRYPOINT ["java", "-jar", "/commons.jar"]