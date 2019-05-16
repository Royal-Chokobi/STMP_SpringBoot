FROM openjdk:8-jdk-alpine
ADD target/smtp-1.0.jar app.jar
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]