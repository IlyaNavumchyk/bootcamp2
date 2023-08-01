FROM openjdk:17-alpine
ARG JAR_FILE=controller/target/*.jar
WORKDIR /opt/app
COPY ${JAR_FILE} bootcamp.jar
ENTRYPOINT ["java","-jar","bootcamp.jar"]