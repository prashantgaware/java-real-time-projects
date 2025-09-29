FROM alpine/java:21-jdk
LABEL authors="prashant"

COPY target/movie-services-registry-1.0-SNAPSHOT.jar movie-services-registry.jar
ENTRYPOINT ["java", "-jar", "/movie-services-registry.jar"]