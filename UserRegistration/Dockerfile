FROM openjdk:21
LABEL authors="prashant"
# ARG JAR_FILE=target/UserRegistration-1.0-SNAPSHOT.jar
# ADD ${JAR_FILE} userregistration.jar
COPY target/UserRegistration-1.0-SNAPSHOT.jar userregistration.jar

ENTRYPOINT ["java", "-jar", "/userregistration.jar"]
