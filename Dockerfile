#base image
FROM openjdk:15-slim as docker-rest-service
#env
ENV LOG_LEVEL INFO
#copy jar from target to image
ADD docker-rest-service/target/docker-rest-service-1.0.jar /usr/src/docker-rest-service-1.0.jar
#set work dir to jar-file path
WORKDIR /usr/src
#port expose
EXPOSE 8080
#start jar inside container
ENTRYPOINT java -Dlogging.level.org.fintech.tinkoff=$LOG_LEVEL -Dspring.datasource.url=$JDBC_URL -Dspring.datasource.username=$JDBC_USER -Dspring.datasource.password=$JDBC_PASS -jar docker-rest-service-1.0.jar


#base image
FROM openjdk:15-slim as docker-rest-service-meta
#env
ENV LOG_LEVEL INFO
#copy jar from target to image
ADD docker-rest-service-meta/target/docker-rest-service-meta-1.0.jar /usr/src/docker-rest-service-meta-1.0.jar
#set work dir to jar-file path
WORKDIR /usr/src
#port expose
EXPOSE 8080
#start jar inside container
ENTRYPOINT java -Dlogging.level.org.fintech.tinkoff=$LOG_LEVEL -Dspring.datasource.url=$JDBC_URL -Dspring.datasource.username=$JDBC_USER -Dspring.datasource.password=$JDBC_PASS -jar docker-rest-service-meta-1.0.jar