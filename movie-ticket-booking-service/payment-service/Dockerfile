FROM alpine/java:21-jdk
LABEL authors="prashant"

COPY target/payment-service-1.0-SNAPSHOT.jar payment-service.jar
ENTRYPOINT ["java", "-jar", "/payment-service.jar"]