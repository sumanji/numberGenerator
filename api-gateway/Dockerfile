FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar apiGateway.jar
ENTRYPOINT ["java","-jar","/apiGateway.jar"]