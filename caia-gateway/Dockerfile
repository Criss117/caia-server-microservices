FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} caia-gateway.jar
ENTRYPOINT ["java","-jar","/caia-gateway.jar"]