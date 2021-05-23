# For Java 8, try this
FROM openjdk:8-jre-alpine

# Refer to Maven build -> finalName
ARG JAR_FILE=target/spring-shorturl-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-shorturl-0.0.1-SNAPSHOT.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]