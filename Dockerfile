# clonar o projeto e mudar a branch para master

FROM alpine:3.8 AS GIT_PROJECT
ARG api_instance=$api_instance
RUN apk update
RUN apk add git
RUN git clone "https://github.com/rhuanpablo13/deliver-desafio.git"
RUN cd deliver-desafio && git checkout master

#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS MAVEN_BUILD
COPY --from=GIT_PROJECT ./deliver-desafio ./
RUN cd ./
RUN mvn clean package -DskipTests

# executar o projeto buildado
FROM openjdk:11
COPY --from=MAVEN_BUILD target/deliver-0.0.1-SNAPSHOT.jar ./
CMD java -Xmx1024m -Xms1024m -server -Dspring.profiles.active=${PROFILE} -jar deliver-0.0.1-SNAPSHOT.jar