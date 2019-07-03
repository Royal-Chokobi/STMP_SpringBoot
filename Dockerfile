FROM openjdk:8
ADD target/smtp-1.0.jar app.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","/app.jar"]