FROM openjdk:17
ARG WAR_FILE=build/libs/Real_java_spring-0.0.1-SNAPSHOT.war
COPY ${WAR_FILE} app.war
ENTRYPOINT ["java","-jar","/app.war"]
EXPOSE 8080
