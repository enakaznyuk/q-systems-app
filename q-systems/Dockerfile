# # Use a base image with Java 17
# FROM openjdk:17-jdk-slim
#
# # Set the working directory
# WORKDIR /app
#
# # Copy the Spring Boot application JAR file into the container
# COPY target/*.jar app.jar
#
# # Set the command to run the Spring Boot application
# CMD ["java", "-jar", "app.jar"]

FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
EXPOSE 8081
ENTRYPOINT ["java","-jar","/home/app/target/spring_rest_docker.jar"]