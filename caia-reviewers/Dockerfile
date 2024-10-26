FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} caia-reviewers.jar
ENTRYPOINT ["java","-jar","/caia-reviewers.jar"]