FROM alpine/java:21-jdk
LABEL authors="prashant"

COPY target/booking-service-1.0-SNAPSHOT.jar booking-service.jar
ENTRYPOINT ["java", "-jar", "/booking-service.jar"]