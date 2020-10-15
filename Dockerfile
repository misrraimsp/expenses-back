FROM openjdk:latest
ADD target/autentia-challenge-back.jar autentia-challenge-back.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "autentia-challenge-back.jar"]
