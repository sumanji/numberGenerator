FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar eurekaserver.jar
ENTRYPOINT ["java","-jar","/eurekaserver.jar"]