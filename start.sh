docker compose down

docker image rm caia-eureka:latest
# docker image rm caia-users:latest
# docker image rm caia-conferences:latest
# docker image rm caia-reviewers:latest
# docker image rm caia-papers:latest
# docker image rm caia-gateway:latest

mvn clean install -f ./caia-eureka/pom.xml -DskipTests
# mvn clean install -f ./caia-users/pom.xml -DskipTests
# mvn clean install -f ./caia-conferences/pom.xml -DskipTests
# mvn clean install -f ./caia-reviewers/pom.xml -DskipTests
# mvn clean install -f ./caia-papers/pom.xml -DskipTests
# mvn clean install -f ./caia-gateway/pom.xml -DskipTests

docker build --tag caia-eureka:latest -f ./caia-eureka/Dockerfile ./caia-eureka
# docker build --tag caia-conferences:latest -f ./caia-conferences/Dockerfile ./caia-conferences
# docker build --tag caia-users:latest -f ./caia-users/Dockerfile ./caia-users
# docker build --tag caia-reviewers:latest -f ./caia-reviewers/Dockerfile ./caia-reviewers
# docker build --tag caia-papers:latest -f ./caia-papers/Dockerfile ./caia-papers
# docker build --tag caia-gateway:latest -f ./caia-gateway/Dockerfile ./caia-gateway

docker compose up -d