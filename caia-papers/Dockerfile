FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} caia-papers.jar
ENTRYPOINT ["java","-jar","/caia-papers.jar"]