FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar createNumber.jar
ENTRYPOINT ["java","-jar","/createNumber.jar"]